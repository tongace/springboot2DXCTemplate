package com.dxc.application.feature.gimmaster.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchGimHeaderDTO {
    private List<String> searchGimTypes;
    private String searchGimDesc;
    private String searchActiveFlag;
}
