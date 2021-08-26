package com.dxc.application.feature.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatedUserMaster {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String picture;
}
