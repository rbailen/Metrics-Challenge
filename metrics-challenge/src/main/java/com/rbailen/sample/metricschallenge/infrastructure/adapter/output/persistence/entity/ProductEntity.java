package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SALES_UNITS", nullable = false)
    private Integer salesUnits;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_stock", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "size")
    @Column(name = "quantity")
    private Map<String, Integer> stock;

}
