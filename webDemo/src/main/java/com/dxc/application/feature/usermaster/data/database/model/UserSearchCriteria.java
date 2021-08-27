package com.dxc.application.feature.usermaster.data.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchCriteria {
    private String searchCitizenId;
    private String searchFirstName;
    private String searchLastName;
}
