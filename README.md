# MetricsService

POC Spring Boot app for recording metrics in InfluxDB 2.x.

## Run

1. Start InfluxDB: `docker compose up -d`
   (UI: http://localhost:8086, login admin / admin12345)
2. Start the app: `mvnw.cmd spring-boot:run` (or run `MetricsServiceApplication` in IntelliJ)
3. Check the connection: `curl http://localhost:8080/api/health/influx`
   (more health curls in `curls/health.sh`)

Connection settings are in `src/main/resources/application.yml` and can be overridden with
`INFLUX_URL`, `INFLUX_TOKEN`, `INFLUX_ORG`, `INFLUX_BUCKET`.
