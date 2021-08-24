package com.dxc.application.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequestMapping("/personmaster")
public class PersonsMasterTestController {

    @GetMapping()
    public String initialHTML(Model model) {
        return "views/personsmastertest.html";
    }

    @GetMapping("/js/persontest.js")
    public String initialJS(Model model) {
        return "js/persontest.js";
    }
    @GetMapping("/js/persontest-call-api.js")
    public String initialJSApi(Model model) {
        return "js/persontest-call-api.js";
    }

}
