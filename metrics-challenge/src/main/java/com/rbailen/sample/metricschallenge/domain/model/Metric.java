package com.rbailen.sample.metricschallenge.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Metric {

    private MetricType metric;

    private double weight;

}
