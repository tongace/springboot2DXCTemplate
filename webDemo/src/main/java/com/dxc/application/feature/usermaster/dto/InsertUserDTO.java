package com.dxc.application.feature.usermaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class InsertUserDTO {

    private String citizenId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private Integer pictureId;
    private String createdBy;
    private String modifiedBy;
}
