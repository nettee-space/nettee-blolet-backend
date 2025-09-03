## 0. 빌드
#FROM gradle:8.10.1-jdk21 AS builder
#
## 작업 디렉토리
#WORKDIR /workspace
#
## Gradle 캐시 최적화: build.gradle, settings.gradle, gradle.properties 먼저 복사
#COPY build.gradle.kts settings.gradle.kts gradle.properties gradlew ./
#COPY gradle ./gradle
#RUN ./gradlew --version
#
## 의존성만 먼저 다운
#RUN ./gradlew dependencies --no-daemon || return 0
#
## 소스 복사
#COPY . .
#
## bootJar 빌드
#RUN ./gradlew :main-runner:bootJar --no-daemon

# 1. Base image: OpenJDK 21 slim
FROM openjdk:21-slim

# 2. 환경 변수로 local 프로파일 지정 → 도커 컴포즈에서 제공 (도커 파일은 여러 환경에 실행될 수 있어야 하므로 이곳에서 주입 X)
#ENV SPRING_PROFILES_ACTIVE=local

# 3. 작업 디렉토리 설정
WORKDIR /app

# 4. 실행할 JAR 복사
COPY ./monolith/main-runner/build/libs/main-runner-0.0.1-SNAPSHOT.jar /app/app.jar
#COPY --from=builder /workspace/monolith/main-runner/build/libs/main-runner-0.0.1-SNAPSHOT.jar /app/app.jar

# 5. 포트 노출
EXPOSE 8080

# 6. 앱 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
