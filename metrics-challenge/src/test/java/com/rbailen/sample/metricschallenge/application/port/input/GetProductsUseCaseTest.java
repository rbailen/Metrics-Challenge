package com.rbailen.sample.metricschallenge.application.port.input;

import com.rbailen.sample.metricschallenge.domain.model.Metric;
import com.rbailen.sample.metricschallenge.domain.model.MetricType;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetProductsUseCaseTest {

    @Autowired
    private GetProductsUseCase getProductsUseCase;

    @Test
    void getProductWithLowestSalesUnitsWithOneStrategy() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(4, products.get(5).getId());
        assertEquals("PLEATED T-SHIRT", products.get(5).getName());
    }

    @Test
    void getProductWithHighestSalesUnitsWithOneStrategy() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(5, products.get(0).getId());
        assertEquals("CONTRASTING LACE T-SHIRT", products.get(0).getName());
    }

    @Test
    void getProductSalesWithSalesUnitsAndWeightZeroWithOneStrategy() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(0).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(1, products.get(0).getId());
        assertEquals("V-NECH BASIC SHIRT", products.get(0).getName());
    }

    @Test
    void getProductWithLowestStockWithOneStrategy() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(5, products.get(5).getId());
        assertEquals("CONTRASTING LACE T-SHIRT", products.get(5).getName());
    }

    @Test
    void getProductWithHighestStockWithOneStrategy() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(4, products.get(0).getId());
        assertEquals("PLEATED T-SHIRT", products.get(0).getName());
    }

    @Test
    void getProductWithLowestSalesUnitsWithTwoStrategies() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(100).build(),
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(0).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(4, products.get(5).getId());
        assertEquals("PLEATED T-SHIRT", products.get(5).getName());
    }

    @Test
    void getProductWithHighestSalesUnitsWithTwoStrategies() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(100).build(),
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(0).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(5, products.get(0).getId());
        assertEquals("CONTRASTING LACE T-SHIRT", products.get(0).getName());
    }

    @Test
    void getProductWithLowestStockWithTwoStrategies() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(0).build(),
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(4, products.get(0).getId());
        assertEquals("PLEATED T-SHIRT", products.get(0).getName());
    }

    @Test
    void getProductWithHighestStockWithTwoStrategies() {
        List<Metric> metrics = Arrays.asList(
                Metric.builder().metric(MetricType.SALES_UNITS).weight(0).build(),
                Metric.builder().metric(MetricType.STOCK_RATIO).weight(100).build()
        );

        List<Product> products = getProductsUseCase.getProducts(metrics);

        assertEquals(5, products.get(5).getId());
        assertEquals("CONTRASTING LACE T-SHIRT", products.get(5).getName());
    }

}
