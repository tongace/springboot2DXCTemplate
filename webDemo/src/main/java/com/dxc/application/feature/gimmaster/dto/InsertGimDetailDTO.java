package com.dxc.application.feature.gimmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class InsertGimDetailDTO {
    private String gimType;
    private String gimCd;
    private String gimValue;
    private String field1;
    private String field2;
    private String field3;
    private String activeFlag;
    private String createdBy;
    private String modifiedBy;
}
