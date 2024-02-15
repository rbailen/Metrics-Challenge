package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.mapper;

import com.rbailen.sample.metricschallenge.domain.model.Product;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductPersistenceMapper {

    List<Product> toProductList (List<ProductEntity> productEntities);

}
