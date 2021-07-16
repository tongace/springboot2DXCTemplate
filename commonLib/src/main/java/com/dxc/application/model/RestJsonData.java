package com.dxc.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestJsonData<T> {
    private String message;
    private BigDecimal rowCount;
    private T data;
}
