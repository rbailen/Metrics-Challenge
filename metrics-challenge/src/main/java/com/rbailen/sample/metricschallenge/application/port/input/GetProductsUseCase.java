package com.rbailen.sample.metricschallenge.application.port.input;

import com.rbailen.sample.metricschallenge.domain.model.Metric;
import com.rbailen.sample.metricschallenge.domain.model.Product;

import java.util.List;

public interface GetProductsUseCase {

    List<Product> getProducts(List<Metric> metrics);

}
