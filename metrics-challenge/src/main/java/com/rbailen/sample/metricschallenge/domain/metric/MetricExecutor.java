package com.rbailen.sample.metricschallenge.domain.metric;

import com.rbailen.sample.metricschallenge.domain.model.Product;

public interface MetricExecutor {

    double executeMetric(double weight, Product product);

}
