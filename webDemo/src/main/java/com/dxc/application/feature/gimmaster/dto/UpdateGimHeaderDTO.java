package com.dxc.application.feature.gimmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class UpdateGimHeaderDTO {
    private String gimType;
    private String gimDesc;
    private Integer cdLength;
    private String field1Label;
    private String field2Label;
    private String field3Label;
    private String activeFlag;
    private String modifiedBy;
    private Date modifiedDt;
}
