package com.dxc.application.feature.gimmaster.data.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GimHeaderSearchCriteria {
    private String[] gimTypes;
    private String gimDesc;
    private String activeFlag;
}
