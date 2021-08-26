package com.dxc.application.feature.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchUserMaster {
    @NotBlank
    private String searchCitizenId;
    @NotBlank
    private String searchFirstName;
    @NotBlank
    private String searchLastName;
}
