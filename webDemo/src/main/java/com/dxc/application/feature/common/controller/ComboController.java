package com.dxc.application.feature.common.controller;

import com.dxc.application.feature.common.data.database.model.Combo;
import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.feature.common.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/combo")
public class ComboController {
    private CommonService commonService;

    @Autowired
    public ComboController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping(value = "/gimtypecombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestJsonData<List<Combo>> getGimTypeCombo() {
        return new RestJsonData<>(null, null, commonService.getGimTypeCombo());
    }

    @GetMapping(value = "/activeflagcombo", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestJsonData<List<Combo>> getActiveFlagCombo() {
        return new RestJsonData<>(null, null, commonService.getActiveFlagCombo());
    }
    @GetMapping(value = "/dropdown", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestJsonData<List<Combo>> getAutoCompleteCombo(@RequestParam("messageCode") String messageCode) {
        if(StringUtils.isNotBlank(messageCode)){
            return new RestJsonData<>(null, null, commonService.getAutoCompleteCombo(messageCode));
        }else{
            return new RestJsonData<>(null, null, new ArrayList<>());
        }
    }
}
