package com.dxc.application.feature.dto;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class NewUserMaster {
    @NotEmpty
    private String citizenId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private Date birthDate;
    @NotEmpty
    private String address;
    @NotEmpty
    private String picture;
}
