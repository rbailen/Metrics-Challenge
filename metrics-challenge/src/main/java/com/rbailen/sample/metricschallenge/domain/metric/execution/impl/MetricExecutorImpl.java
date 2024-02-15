package com.rbailen.sample.metricschallenge.domain.metric.execution.impl;

import com.rbailen.sample.metricschallenge.domain.metric.MetricExecutor;
import com.rbailen.sample.metricschallenge.domain.model.Product;

import java.util.List;

public abstract class MetricExecutorImpl implements MetricExecutor {

    @Override
    public double executeMetric(double weight, Product product) {
        return applyMetric(weight, product);
    }

    public abstract double applyMetric(double weight, Product product);

}
