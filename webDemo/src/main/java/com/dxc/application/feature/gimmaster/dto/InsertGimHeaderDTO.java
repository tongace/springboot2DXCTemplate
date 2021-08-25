package com.dxc.application.feature.gimmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties
public class InsertGimHeaderDTO {
    @NotBlank
    private String gimType;
    @NotBlank
    private String gimDesc;
    @NotBlank
    private BigDecimal cdLength;
    @NotBlank
    private String field1Label;
    @NotBlank
    private String field2Label;
    @NotBlank
    private String field3Label;
    @NotBlank
    private String activeFlag;
    @NotBlank
    private String createdBy;
    @NotBlank
    private String modifiedBy;
}
