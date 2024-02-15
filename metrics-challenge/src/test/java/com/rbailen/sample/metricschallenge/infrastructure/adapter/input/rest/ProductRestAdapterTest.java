package com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest;

import com.rbailen.sample.metricschallenge.application.port.input.GetProductsUseCase;
import com.rbailen.sample.metricschallenge.domain.model.MetricType;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.data.request.ProductsQueryRequest;
import com.rbailen.sample.metricschallenge.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.rbailen.sample.metricschallenge.ProductsUtilsTest.asJsonString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ProductRestAdapterTest {

    private GetProductsUseCase getProductsUseCase;

    private ProductRestMapper productRestMapper;

    private Tracer tracer;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        getProductsUseCase = mock(GetProductsUseCase.class);
        productRestMapper = mock(ProductRestMapper.class);

        tracer = mock(Tracer.class);
        Span span = mock(Span.class);
        TraceContext traceContext = mock(TraceContext.class);
        when(tracer.currentSpan()).thenReturn(span);
        when(tracer.currentSpan().context()).thenReturn(traceContext);
        when(tracer.currentSpan().context().traceId()).thenReturn(UUID.randomUUID().toString());

        ProductRestAdapter productRestAdapter = new ProductRestAdapter(getProductsUseCase, productRestMapper, tracer);

        mockMvc = standaloneSetup(productRestAdapter).build();
    }

    @Test
    void getProductsStatusCode200() throws Exception {
        List<ProductsQueryRequest> productsQueryRequestList = Arrays.asList(
                ProductsQueryRequest.builder().metric(MetricType.SALES_UNITS).weight(100.0).build()
        );
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/products")
                        .content(asJsonString(productsQueryRequestList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProductsStatusCode400() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/products"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductsStatusCodeThrowWeightExceedsLimitException() throws Exception {
        List<ProductsQueryRequest> productsQueryRequestList = Arrays.asList(
                ProductsQueryRequest.builder().metric(MetricType.SALES_UNITS).weight(105.0).build()
        );
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/products")
                        .content(asJsonString(productsQueryRequestList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
    }

}
