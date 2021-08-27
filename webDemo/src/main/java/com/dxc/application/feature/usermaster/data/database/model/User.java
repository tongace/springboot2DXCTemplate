package com.dxc.application.feature.usermaster.data.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
