package com.dxc.application.feature.gimmaster.data.database.model;

import lombok.Data;

import java.util.Date;

@Data
public class GimHeader {
    private String gimType;
    private String gimDesc;
    private Integer cdLength;
    private String field1Label;
    private String field2Label;
    private String field3Label;
    private String activeFlag;
    private String createdBy;
    private Date createdDt;
    private String modifiedBy;
    private Date modifiedDt;
}
