package com.dxc.application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/usermaster")
public class UserMasterController {

    @Autowired
    public UserMasterController() {
    }

    @GetMapping()
    public String initialHTML(Model model) {
        return "views/usermaster.html";
    }

    @GetMapping("/js/usermaster.js")
    public String initialJS(Model model) {
        return "js/usermaster.js";
    }

    @GetMapping("/js/usermaster-call-api.js")
    public String initialJSApi(Model model) {
        return "js/usermaster-call-api.js";
    }

}
