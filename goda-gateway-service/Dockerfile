# Sử dụng multi-stage build để tận dụng caching của Maven dependencies
# Stage 1: Build môi trường
FROM maven:3.6.3-openjdk-17-slim AS build

# Sao chép POM và source code vào trong image
COPY src /app/src
COPY pom.xml /app

# Thiết lập working directory
WORKDIR /app

# Chạy Maven build (bạn có thể bỏ qua test với -DskipTests nếu muốn)
RUN mvn package -DskipTests

# Stage 2: Chạy ứng dụng
FROM openjdk:17

LABEL maintainer="anhhq.workit@gmail.com"
LABEL service="goda-gateway-service"

# Tạo thư mục để chứa ứng dụng
WORKDIR /app

# Sao chép file JAR từ stage build
COPY --from=build /app/target/goda-gateway-service-0.0.1-SNAPSHOT.jar /app/goda-gateway-service.jar

# Mở port mà service sẽ sử dụng
EXPOSE 8080

# Chạy ứng dụng
CMD ["java", "-jar", "goda-gateway-service.jar"]
