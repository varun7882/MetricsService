package com.varun.metrics.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 3.x has no auto-configuration for InfluxDB 2.x, so the client is wired here.
 * Creating the client does not open a connection; it connects lazily on first call.
 */
@Configuration
public class InfluxConfig {

    @Bean(destroyMethod = "close")
    public InfluxDBClient influxDBClient(InfluxProperties props) {
        return InfluxDBClientFactory.create(
                props.url(),
                props.token().toCharArray(),
                props.org(),
                props.bucket());
    }
}
