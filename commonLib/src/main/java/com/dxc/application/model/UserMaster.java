package com.dxc.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "Details about the user personal information")
@Data
public class UserMaster {
    @ApiModelProperty(notes = "The unique 13 numbers of Citizen ID", example = "3328100036799", required = true)
    private String citizenId;
    @ApiModelProperty(notes = "The person's first name")
    private String firstName;
    @ApiModelProperty(notes = "The person's last name")
    private String lastName;
    @ApiModelProperty(notes = "The person's birth date", example = "31/12/1988")
    private Date birthDate;
    @ApiModelProperty(notes = "The person's address")
    private String address;
    @ApiModelProperty(notes = "The person's picture")
    private String picture;
}
