package com.dxc.application.feature.gimmaster.dto;

import lombok.Data;

@Data
public class SearchGimHeader {
    private String[] searchGimTypes;
    private String searchGimDesc;
    private String searchActiveFlag;
}
