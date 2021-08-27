package com.dxc.application.feature.usermaster.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserResultDTO {
    private String citizenId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private Integer pictureId;
    private String createdBy;
    private Date createdDt;
    private String modifiedBy;
    private Date modifiedDt;
}
