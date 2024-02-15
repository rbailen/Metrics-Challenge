package com.rbailen.sample.metricschallenge.application.service;

import com.rbailen.sample.metricschallenge.application.port.input.GetProductsUseCase;
import com.rbailen.sample.metricschallenge.application.port.output.ProductOutputPort;
import com.rbailen.sample.metricschallenge.domain.metric.MetricExecutor;
import com.rbailen.sample.metricschallenge.domain.metric.strategy.SalesUnitsStrategyImpl;
import com.rbailen.sample.metricschallenge.domain.metric.strategy.StockRatioStrategyImpl;
import com.rbailen.sample.metricschallenge.domain.model.Metric;
import com.rbailen.sample.metricschallenge.domain.model.MetricType;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

@AllArgsConstructor
public class ProductService implements GetProductsUseCase {

    private EnumMap<MetricType, MetricExecutor> executorStrategyMap;

    private final SalesUnitsStrategyImpl salesUnitsStrategy;

    private final StockRatioStrategyImpl stockRatioStrategy;

    private final ProductOutputPort productOutputPort;

    public ProductService(SalesUnitsStrategyImpl salesUnitsStrategy, StockRatioStrategyImpl stockRatioStrategy, ProductOutputPort productOutputPort){
        this.salesUnitsStrategy = salesUnitsStrategy;
        this.stockRatioStrategy = stockRatioStrategy;
        this.productOutputPort = productOutputPort;
    }

    @PostConstruct
    public void loadStrategies(){
        executorStrategyMap = new EnumMap<>(MetricType.class);

        executorStrategyMap.put(MetricType.SALES_UNITS, salesUnitsStrategy);
        executorStrategyMap.put(MetricType.STOCK_RATIO, stockRatioStrategy);
    }

    private MetricExecutor getStrategy(MetricType metricType){
        return executorStrategyMap.get(metricType);
    }

    @Override
    public List<Product> getProducts(List<Metric> metrics) {
        List<Product> productList = productOutputPort.getProducts();

        productList.sort(Comparator.comparingDouble(product -> applyMetrics((Product) product, metrics)).reversed());

        return productList;
    }

    private double applyMetrics(Product product, List<Metric> metrics) {
        return metrics.stream()
                .mapToDouble(metric -> getStrategy(metric.getMetric()).executeMetric(metric.getWeight(), product))
                .sum();
    }

}
