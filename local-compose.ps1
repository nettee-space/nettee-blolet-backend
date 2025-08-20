param()

# 파일 확인
if (-not (Test-Path "./docker-compose-local.yml")) {
    Write-Host "docker-compose-local.yml 이(가) 프로젝트 루트에 필요합니다."
    exit 1
}

# 0) Base image 사전 준비
Write-Host "Base image 확인 중..."
if (-not (docker image inspect openjdk:21-slim 2>$null)) { docker pull openjdk:21-slim }
if (-not (docker image inspect gradle:8.10.1-jdk21 2>$null)) { docker pull gradle:8.10.1-jdk21 }

# JAR 확인 및 빌드
if (-not (Test-Path "monolith/main-runner/build/libs/main-runner-0.0.1-SNAPSHOT.jar")) {
    Write-Host "main-runner JAR이 없어 빌드합니다..."
    ./gradlew --version
    docker run --rm `
        -v "${PWD}:/workspace" `
        -v "$HOME/.gradle:/home/gradle/.gradle" `
        -w /workspace `
        gradle:8.10.1-jdk21 `
        ./gradlew :main-runner:bootJar --no-daemon
    if ($LASTEXITCODE -ne 0) { exit 1 }
}

# 1) 인프라 기동
./monolith-compose up -d
if ($LASTEXITCODE -ne 0) { exit 1 }

# 2) 인프라 헬스웨이트
$services = @("demo_postgres14", "demo_redis")
Write-Host "인프라 헬스체크 대기 중..."
foreach ($s in $services) {
    Write-Host " - $s: " -NoNewline
    $tries = 0
    while ($true) {
        $cid = docker compose -f docker-compose-monolith.yml ps -q $s 2>$null
        if (-not $cid) { Start-Sleep -Seconds 2; continue }
        $status = docker inspect -f '{{.State.Health.Status}}' $cid 2>$null
        if ($status -eq "healthy") {
            Write-Host " OK"
            break
        }
        $tries++
        if ($tries -gt 180) {
            Write-Host "시간 초과(≈15분). 상태를 확인하세요."
            exit 1
        }
        Write-Host -NoNewline "."
        Start-Sleep -Seconds 5
    }
}

# 3) 앱 기동
docker compose -f docker-compose-local.yml up -d --build
if ($LASTEXITCODE -ne 0) { exit 1 }

Write-Output "🚃 로컬 앱 부트 시작: http://localhost:8080"

Write-Output "앱 헬스체크 대기 중..."
$tries = 0
$response = ""

while ([string]::IsNullOrEmpty($response)) {
    try {
        $response = curl -s http://localhost:8080/actuator/health | Out-String
    } catch {
        $response = ""
    }
    $tries++
    if ($tries -gt 180) {
        Write-Output "시간 초과(≈3분). 상태를 확인하세요."
        exit 1
    }
    Start-Sleep -Seconds 1
}

# status 값 추출 (정규식 사용)
if ($response -match '"status":"([^"]*)"') {
    $status = $matches[1]
} else {
    $status = "UNKNOWN"
}

switch ($status) {
    "UP"   { $presentation = "✅ 정상 기동" }
    "DOWN" { $presentation = "❌ 일부 기능 작동하지 않음" }
    default { $presentation = "⚠️ 기타" }
}

Write-Output "✅ 로컬 앱 기동 완료: http://localhost:8080 (상태: $presentation)"