package com.rbailen.sample.metricschallenge.domain.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    private String name;

    private Integer salesUnits;

    private Map<String, Integer> stock;

}
