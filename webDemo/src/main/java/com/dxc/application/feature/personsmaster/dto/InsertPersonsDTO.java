package com.dxc.application.feature.personsmaster.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InsertPersonsDTO {
    private String citizenId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String fileName;
}
