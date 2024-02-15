package com.rbailen.sample.metricschallenge.application.port.output;

import com.rbailen.sample.metricschallenge.domain.model.Product;

import java.util.List;

public interface ProductOutputPort {

    List<Product> getProducts();

}
