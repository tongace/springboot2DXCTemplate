package com.dxc.application.feature.gimmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class UpdateGimDetailDTO {
    private String gimType;
    private String gimCd;
    private String gimValue;
    private String field1;
    private String field2;
    private String field3;
    private String activeFlag;
    private String modifiedBy;
    private Date modifiedDt;
}
