# Gradle 빌드 단계
FROM gradle:7.2.0-jdk11 as builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 캐시를 별도로 빌드하기 위해 settings.gradle 파일 복사
COPY settings.gradle .
# Gradle 빌드 설정 파일을 복사
COPY build.gradle .

# 의존성 다운로드를 위해 빈 프로젝트를 빌드
RUN gradle build --no-daemon

# 소스 코드를 복사하여 애플리케이션을 빌드
COPY src src
RUN gradle build --no-daemon

# 최종 빌드 스테이지
FROM adoptopenjdk/openjdk19:latest

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]