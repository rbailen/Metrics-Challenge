package com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.response;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsQueryResponse {

    private Long id;

    private String name;

    private Integer salesUnits;

    private Map<String, Integer> stock;

}
