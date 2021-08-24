package com.dxc.application.controllers;

import com.dxc.application.services.GimMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/vue")
public class vueController {

    private GimMasterService personalService;

    @Autowired
    public vueController(GimMasterService personalService) {
        this.personalService = personalService;
    }

    @GetMapping()
    public String initialHTML(Model model) {
        return "views/vue.html";
    }

    @GetMapping("/js/vue.js")
    public String initialJS(Model model) {
        return "js/vue.js";
    }

    @GetMapping("/js/vue-call-api.js")
    public String initialJSApi(Model model) {
        return "js/vue-call-api.js";
    }
}
