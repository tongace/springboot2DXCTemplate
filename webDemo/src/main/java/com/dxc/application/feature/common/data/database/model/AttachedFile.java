package com.dxc.application.feature.common.data.database.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AttachedFile {
    private BigDecimal id;
    private String fileName;
    private String fileContentType;
    private byte[] fileData;
    private String createdBy;
    private Date createdDt;
}
