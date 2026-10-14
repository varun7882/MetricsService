package com.varun.metrics;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MetricsServiceApplicationTests {

    @Test
    void contextLoads() {
        // The Influx client connects lazily, so this passes without InfluxDB running.
    }
}
