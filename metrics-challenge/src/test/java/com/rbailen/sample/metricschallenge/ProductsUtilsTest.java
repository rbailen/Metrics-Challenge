package com.rbailen.sample.metricschallenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbailen.sample.metricschallenge.domain.model.Product;
import java.util.Arrays;
import java.util.List;

public class ProductsUtilsTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> productsExample() {
        return Arrays.asList(
                Product.builder().name("V-NECH BASIC SHIRT").salesUnits(100).build(),
                Product.builder().name("CONTRASTING FABRIC T-SHIRT").salesUnits(50).build(),
                Product.builder().name("RAISED PRINT T-SHIRT").salesUnits(80).build(),
                Product.builder().name("PLEATED T-SHIRT").salesUnits(3).build(),
                Product.builder().name("CONTRASTING LACE T-SHIRT").salesUnits(650).build(),
                Product.builder().name("SLOGAN T-SHIRT").salesUnits(20).build()
        );
    }

}
