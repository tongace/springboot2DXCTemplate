package com.dxc.application.feature.home.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private BigDecimal id;
    private String firstName;
    private String lastName;
}
