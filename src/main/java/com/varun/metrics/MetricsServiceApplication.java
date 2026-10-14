package com.varun.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MetricsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsServiceApplication.class, args);
    }
}
