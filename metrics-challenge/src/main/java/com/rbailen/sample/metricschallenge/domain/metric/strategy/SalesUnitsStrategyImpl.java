package com.rbailen.sample.metricschallenge.domain.metric.strategy;

import com.rbailen.sample.metricschallenge.domain.metric.execution.impl.MetricExecutorImpl;
import com.rbailen.sample.metricschallenge.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class SalesUnitsStrategyImpl extends MetricExecutorImpl {

    @Override
    public double applyMetric(double weight, Product product) {
        return weight * product.getSalesUnits();
    }

}
