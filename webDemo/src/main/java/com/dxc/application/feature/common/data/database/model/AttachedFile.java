package com.dxc.application.feature.common.data.database.model;

import lombok.Data;

import java.util.Date;

@Data
public class AttachedFile {
    private Integer id;
    private String fileName;
    private String fileContentType;
    private byte[] fileData;
    private String createdBy;
    private Date createdDt;
}
