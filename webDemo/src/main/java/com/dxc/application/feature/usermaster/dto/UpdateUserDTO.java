package com.dxc.application.feature.usermaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class UpdateUserDTO {
    private String citizenId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private Integer pictureId;
    private String modifiedBy;
    private Date modifiedDt;
}
