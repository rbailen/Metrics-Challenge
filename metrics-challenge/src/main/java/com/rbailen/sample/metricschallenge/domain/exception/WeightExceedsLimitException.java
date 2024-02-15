package com.rbailen.sample.metricschallenge.domain.exception;

public class WeightExceedsLimitException extends RuntimeException {

    public WeightExceedsLimitException(String message) {
        super(message);
    }

}
