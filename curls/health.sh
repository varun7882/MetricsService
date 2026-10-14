#!/usr/bin/env bash
# Health check curls for MetricsService.
# Run from Git Bash: ./curls/health.sh   (or paste a single command into any terminal)
BASE_URL="${BASE_URL:-http://localhost:8080}"

# 1. App -> InfluxDB connection check (expect HTTP 200 and "status": "UP")
curl -s -i "$BASE_URL/api/health/influx"
echo

# 2. Just the HTTP status code (200 = UP, 503 = DOWN)
curl -s -o /dev/null -w "HTTP %{http_code}\n" "$BASE_URL/api/health/influx"

# 3. InfluxDB directly, bypassing the app (expect HTTP 204)
curl -s -o /dev/null -w "InfluxDB ping: HTTP %{http_code}\n" "http://localhost:8086/ping"
