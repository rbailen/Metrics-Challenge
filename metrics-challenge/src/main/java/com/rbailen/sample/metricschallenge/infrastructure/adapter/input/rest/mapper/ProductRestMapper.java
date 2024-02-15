package com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.mapper;

import com.rbailen.sample.metricschallenge.domain.model.Metric;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.response.ProductsQueryResponse;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.request.ProductsQueryRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductRestMapper {

    List<Metric> toMetricList(List<ProductsQueryRequest> productsQueryRequestList);

    List<ProductsQueryResponse> toProductsQueryResponse(List<Product> products);

}
