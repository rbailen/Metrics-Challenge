package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence;

import com.rbailen.sample.metricschallenge.application.port.output.ProductOutputPort;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductPersistenceAdapterTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductPersistenceMapper productPersistenceMapper;

    private ProductOutputPort productOutputPort;

    @BeforeEach
    void setUp() {
        productPersistenceMapper = Mappers.getMapper(ProductPersistenceMapper.class);
        productOutputPort = new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Test
    void getCourses() {
        List<Product> products = productOutputPort.getProducts();
        assertEquals(6, products.size());
    }

}
