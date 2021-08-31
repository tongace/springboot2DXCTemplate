package com.dxc.application.feature.gimmaster.data.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GimHeaderSearchCriteria {
    private List<String> gimTypes;
    private String gimDesc;
    private String activeFlag;
}
