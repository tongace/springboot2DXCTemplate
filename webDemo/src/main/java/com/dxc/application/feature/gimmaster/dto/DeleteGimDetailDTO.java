package com.dxc.application.feature.gimmaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
@Data
@JsonIgnoreProperties
public class DeleteGimDetailDTO {
    private String gimType;
    private String gimCd;
    private Date modifiedDt;
}
