package com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.request;

import com.rbailen.sample.metricschallenge.domain.model.MetricType;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductsQueryRequest {

    @Parameter(required = true, name = "metric", description = "Metric type", example = "SALES_UNITS/STOCK_RATIO")
    @NotNull(message = "Metric is mandatory")
    private MetricType metric;

    @Parameter(required = true, name = "weight", description = "Weight", example = "100")
    @NotNull(message = "Weight is mandatory")
    @Min(value = 0, message = "Min value is 0")
    @Max(value = 100, message = "Max value is 100")
    private Double weight;

}
