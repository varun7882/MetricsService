package com.varun.metrics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Binds the "influx.*" block from application.yml. */
@ConfigurationProperties(prefix = "influx")
public record InfluxProperties(String url, String token, String org, String bucket) {
}
