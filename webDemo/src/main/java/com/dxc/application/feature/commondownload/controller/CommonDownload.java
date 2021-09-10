package com.dxc.application.feature.commondownload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commondownload")
public class CommonDownload {
    @GetMapping()
    public String initialHTML() {
        return "commondownload/commondownload.html";
    }

    @GetMapping("/js/commondownload.js")
    public String initialJS() {
        return "commondownload/commondownload.js";
    }
}
