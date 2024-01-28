application.yml example
```
server:
  port: 8080

spring:

  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PW}

  jpa:
    hibernate:
      ddl-auto: update
    open-in-view: false
    properties:
      hibernate:
        show_sql: false
        format_sql: true

app:
  jwt:
    access-key: ${JWT_ACCESS_KEY}
    access-expire: ${JWT_ACCESS_EXPIRE}
  s3:
    bucket: ${S3_BUCKET}
    region: ${S3_REGION}
    access-key: ${S3_ACCESS_KEY}
    secret-key: ${S3_SECRET_KEY}

```
