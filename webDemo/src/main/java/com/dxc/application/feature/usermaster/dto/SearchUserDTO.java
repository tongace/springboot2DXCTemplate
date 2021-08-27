package com.dxc.application.feature.usermaster.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchUserDTO {
    private String searchCitizenId;
    private String searchFirstName;
    private String searchLastName;
}
