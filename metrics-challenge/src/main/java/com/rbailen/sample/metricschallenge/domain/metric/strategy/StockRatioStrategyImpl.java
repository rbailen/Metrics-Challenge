package com.rbailen.sample.metricschallenge.domain.metric.strategy;

import com.rbailen.sample.metricschallenge.domain.metric.execution.impl.MetricExecutorImpl;
import com.rbailen.sample.metricschallenge.domain.model.Product;

public class StockRatioStrategyImpl extends MetricExecutorImpl {

    @Override
    public double applyMetric(double weight, Product product) {
        return weight * calculateStock(product);
    }

    private double calculateStock(Product product) {
        return product.getStock().values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
