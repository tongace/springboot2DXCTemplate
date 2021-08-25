package com.dxc.application.feature.gimmaster.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GimHeaderResultDTO {
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
    private String displayActiveFlag;
}
