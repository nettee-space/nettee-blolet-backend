# 1. Base image: OpenJDK 21 slim
FROM openjdk:21-slim

# 2. 환경 변수로 local 프로필 지정
ENV SPRING_PROFILES_ACTIVE=local

# 3. 작업 디렉토리 설정
WORKDIR /app

# 4. 실행할 JAR 복사
COPY ./monolith/main-runner/build/libs/main-runner-0.0.1-SNAPSHOT.jar /app/app.jar

# 5. 포트 노출
EXPOSE 8080

# 6. 앱 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
