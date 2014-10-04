package com.github.jamesmura.costa.web.healthchecks;

import com.codahale.metrics.health.HealthCheck;

public class CostsHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
