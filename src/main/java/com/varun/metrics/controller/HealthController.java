package com.varun.metrics.controller;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.Bucket;
import com.varun.metrics.config.InfluxProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final InfluxDBClient influxDBClient;
    private final InfluxProperties props;

    public HealthController(InfluxDBClient influxDBClient, InfluxProperties props) {
        this.influxDBClient = influxDBClient;
        this.props = props;
    }

    /** GET /api/health/influx : checks the server is reachable and the token can see the bucket. */
    @GetMapping("/influx")
    public ResponseEntity<Map<String, Object>> influx() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("url", props.url());
        body.put("org", props.org());
        body.put("bucket", props.bucket());

        try {
            boolean reachable = Boolean.TRUE.equals(influxDBClient.ping());
            body.put("reachable", reachable);
            if (!reachable) {
                body.put("status", "DOWN");
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
            }

            body.put("version", influxDBClient.version());

            // Exercises the token: fails with 401 if the token is wrong.
            Bucket bucket = influxDBClient.getBucketsApi().findBucketByName(props.bucket());
            body.put("bucketFound", bucket != null);
            body.put("status", bucket != null ? "UP" : "DOWN");
            return ResponseEntity.status(bucket != null ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE).body(body);
        } catch (Exception e) {
            body.put("status", "DOWN");
            body.put("error", e.getClass().getSimpleName() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
        }
    }
}
