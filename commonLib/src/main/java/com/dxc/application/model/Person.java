package com.dxc.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	private String citizenId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String address;
	private String fileName;
	private BigDecimal fileId;
	private String modifiedBy;
	private Date modifiedDt;
}
