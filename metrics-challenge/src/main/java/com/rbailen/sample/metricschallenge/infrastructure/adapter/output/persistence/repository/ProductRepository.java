package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.repository;

import com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
