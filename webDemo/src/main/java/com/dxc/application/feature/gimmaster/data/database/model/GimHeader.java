package com.dxc.application.feature.gimmaster.data.database.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Data
public class GimHeader {
	private String gimType;
	private String gimDesc;
	private BigDecimal cdLength;
	private String field1Label;
	private String field2Label;
	private String field3Label;
	private String activeFlag;
	private String createdBy;
	private Date createdDt;
	private String modifiedBy;
	private Date modifiedDt;
}
