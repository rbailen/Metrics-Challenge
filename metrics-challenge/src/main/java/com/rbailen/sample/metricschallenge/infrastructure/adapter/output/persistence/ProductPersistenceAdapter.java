package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence;

import com.rbailen.sample.metricschallenge.application.port.output.ProductOutputPort;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.entity.ProductEntity;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

    private final ProductRepository productRepository;

    private final ProductPersistenceMapper productPersistenceMapper;

    @Override
    public List<Product> getProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productPersistenceMapper.toProductList(productEntityList);
    }

}
