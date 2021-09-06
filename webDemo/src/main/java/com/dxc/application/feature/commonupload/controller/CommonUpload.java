package com.dxc.application.feature.commonupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commonupload")
public class CommonUpload {
    @GetMapping()
    public String initialHTML() {
        return "commonupload/commonupload.html";
    }

    @GetMapping("/js/commonupload.js")
    public String initialJS() {
        return "commonupload/commonupload.js";
    }
}
