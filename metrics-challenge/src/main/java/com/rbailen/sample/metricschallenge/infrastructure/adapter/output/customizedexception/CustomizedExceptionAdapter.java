package com.rbailen.sample.metricschallenge.infrastructure.adapter.output.customizedexception;

import com.rbailen.sample.metricschallenge.domain.exception.WeightExceedsLimitException;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomizedExceptionAdapter.class);

    private final Tracer tracer;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        String traceId = tracer.currentSpan().context().traceId();
        LOG.error("An internal error has occurred in the request with traceId {}: {}", traceId, ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        problemDetail.setType(URI.create("https://api.products.com/errors/internal-server-error"));

        Map<String, Object> properties = new HashMap<>();
        properties.put("date", LocalDateTime.now());
        problemDetail.setProperties(properties);

        return new ResponseEntity(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WeightExceedsLimitException.class)
    public final ResponseEntity<Object> handleWeightExceedsLimitException(WeightExceedsLimitException ex) {
        return getBadRequestObjectResponseEntity(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream().findFirst().map(ObjectError::getDefaultMessage).orElse(null);

        return getBadRequestObjectResponseEntity(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = ex.getAllValidationResults().stream().findFirst().get()
                .getResolvableErrors().stream().findFirst().get().getDefaultMessage();

        return getBadRequestObjectResponseEntity(errorMessage);
    }

    private ResponseEntity<Object> getBadRequestObjectResponseEntity(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);

        problemDetail.setType(URI.create("https://api.products.com/errors/not-found"));

        Map<String, Object> properties = new HashMap<>();
        properties.put("date", LocalDateTime.now());
        problemDetail.setProperties(properties);

        return new ResponseEntity(problemDetail, HttpStatus.BAD_REQUEST);
    }

}