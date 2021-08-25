package com.dxc.application.feature.personsmaster.dto;

import lombok.Data;

@Data
public class SearchPersonsDTO {
    private String searchCitizenId;
    private String searchFirstName;
    private String searchLastName;
}
