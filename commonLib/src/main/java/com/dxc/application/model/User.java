package com.dxc.application.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private BigDecimal id;
    private String firstName;
    private String lastName;
}
