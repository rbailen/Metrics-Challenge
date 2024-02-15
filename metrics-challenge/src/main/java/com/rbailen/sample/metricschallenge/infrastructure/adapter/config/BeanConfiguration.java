package com.rbailen.sample.metricschallenge.infrastructure.adapter.config;

import com.rbailen.sample.metricschallenge.application.service.ProductService;
import com.rbailen.sample.metricschallenge.domain.metric.strategy.SalesUnitsStrategyImpl;
import com.rbailen.sample.metricschallenge.domain.metric.strategy.StockRatioStrategyImpl;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.ProductPersistenceAdapter;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository, ProductPersistenceMapper productPersistenceMapper) {
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter) {
        return new ProductService(new SalesUnitsStrategyImpl(), new StockRatioStrategyImpl(), productPersistenceAdapter);
    }

}
