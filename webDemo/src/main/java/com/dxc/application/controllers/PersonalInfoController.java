package com.dxc.application.controllers;

import com.dxc.application.constants.MessagesConstants;
import com.dxc.application.exceptions.ApplicationException;
import com.dxc.application.model.GimHeader;
import com.dxc.application.model.RestJsonData;
import com.dxc.application.services.GimMasterService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/personalinfo")
public class PersonalInfoController {

    private GimMasterService personalService;

    @Autowired
    public PersonalInfoController(GimMasterService personalService) {
        this.personalService = personalService;
    }

    @GetMapping()
    public String initialHTML(Model model) {
        return "views/personalinfo.html";
    }

    @GetMapping("/js/personalinfo.js")
    public String initialJS(Model model) {
        return "js/personalinfo.js";
    }

    @GetMapping("/js/personalinfo-call-api.js")
    public String initialJSApi(Model model) {
        return "js/personalinfo-call-api.js";
    }

}
