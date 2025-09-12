FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드 시 전달 가능한 인자 정의
ARG JWT_SECRET
ARG PORT

# 컨테이너 실행 시 환경변수로 사용
ENV JWT_SECRET=${JWT_SECRET}
ENV PORT=${PORT}

# 현재 디렉토리(authServer)의 모든 파일 복사
COPY . .

# Gradle 실행 권한 및 빌드
RUN chmod +x ./gradlew
RUN ./gradlew clean build

CMD ["java", "-jar", "build/libs/resourceServer-0.0.1-SNAPSHOT.jar"]