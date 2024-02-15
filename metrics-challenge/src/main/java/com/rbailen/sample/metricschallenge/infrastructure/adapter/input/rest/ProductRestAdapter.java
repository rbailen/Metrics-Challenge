package com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest;

import com.rbailen.sample.metricschallenge.application.port.input.GetProductsUseCase;
import com.rbailen.sample.metricschallenge.domain.exception.WeightExceedsLimitException;
import com.rbailen.sample.metricschallenge.domain.model.Metric;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.response.ProductsQueryResponse;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.request.ProductsQueryRequest;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import io.micrometer.tracing.Tracer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRestAdapter.class);

    private final GetProductsUseCase getProductsUseCase;

    private final ProductRestMapper productRestMapper;

    private final Tracer tracer;

    @Operation(summary = "Return list of products ordered")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of products",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ProductsQueryResponse.class)))})
    })
    @PostMapping
    public ResponseEntity<List<ProductsQueryResponse>> getProducts(
            @RequestBody @Valid List<ProductsQueryRequest> productsQueryRequestList) {
        validateWeights(productsQueryRequestList);

        String traceId = tracer.currentSpan().context().traceId();
        LOG.info("Beginning of the process with traceId {} to get the products ordered by the metrics {}", traceId, productsQueryRequestList.toString());

        List<Metric> metrics = productRestMapper.toMetricList(productsQueryRequestList);

        List<Product> products = getProductsUseCase.getProducts(metrics);

        LOG.info("Completion of the process with traceId {} to get the products ordered by the metrics {}", traceId, productsQueryRequestList.toString());

        return new ResponseEntity<>(productRestMapper.toProductsQueryResponse(products), HttpStatus.OK);
    }

    private void validateWeights(List<ProductsQueryRequest> productsQueryRequestList){
        double totalWeights = productsQueryRequestList.stream()
                .mapToDouble(ProductsQueryRequest::getWeight)
                .sum();

        if (totalWeights > 100) {
            throw new WeightExceedsLimitException("The sum of weights cannot exceed 100");
        }
    }

}
