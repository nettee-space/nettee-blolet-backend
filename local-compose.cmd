@echo off
setlocal

if not exist "%cd%\docker-compose-local.yml" (
    echo docker-compose-local.yml 이(가) 프로젝트 루트에 필요합니다.
    exit /b 1
)

REM 0) Base image 사전 준비
echo Base image 확인 중...
docker image inspect openjdk:21-slim >nul 2>&1
if errorlevel 1 (
    docker pull openjdk:21-slim
)
REM docker image inspect gradle:8.10.1-jdk21 >nul 2>&1 || docker pull gradle:8.10.1-jdk21

REM main-runner JAR 확인 및 빌드
if not exist "monolith\main-runner\build\libs\main-runner-0.0.1-SNAPSHOT.jar" (
    echo main-runner JAR이 없어 빌드합니다...
    call .\gradlew --version
    docker run --rm ^
        -v "%cd%":/workspace ^
        -v "%USERPROFILE%\.gradle":/home/gradle/.gradle ^
        -w /workspace ^
        gradle:8.10.1-jdk21 ^
        ./gradlew :main-runner:bootJar --no-daemon
    if errorlevel 1 exit /b 1
)

rem 1) 인프라 기동
call .\monolith-compose up -d
if errorlevel 1 exit /b 1

rem 2) 헬스웨이트 (PowerShell)
powershell -NoProfile -Command ^
    "$services=@('demo_postgres14','demo_redis');" ^
    "Write-Host '인프라 헬스체크 대기 중...';" ^
    "foreach($s in $services){" ^
    "  Write-Host (' - ' + $s + ': ') -NoNewline;" ^
    "  $deadline=(Get-Date).AddMinutes(15);" ^
    "  while($true){" ^
    "    $cid = (docker compose -f docker-compose-monolith.yml ps -q $s) 2>$null;" ^
    "    if(-not $cid){ Start-Sleep -Seconds 2; continue }" ^
    "    $status = (docker inspect -f '{{.State.Health.Status}}' $cid) 2>$null;" ^
    "    if($status -eq 'healthy'){ Write-Host ' OK'; break }" ^
    "    if((Get-Date) -gt $deadline){ Write-Error (' '+$s+' 시간 초과'); exit 1 }" ^
    "    Write-Host -NoNewline '.'; Start-Sleep -Seconds 5" ^
    "  }" ^
    "}"

if errorlevel 1 exit /b 1

rem 3) 앱 기동
docker compose -f docker-compose-local.yml up -d --build
if errorlevel 1 exit /b 1

echo 로컬 앱 기동 완료: http://localhost:8080
