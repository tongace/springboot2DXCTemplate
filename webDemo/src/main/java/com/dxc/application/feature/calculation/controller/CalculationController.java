package com.dxc.application.feature.calculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculation")
public class CalculationController {
    @GetMapping()
    public String initialHTML() {
        return "calculation/calculation.html";
    }

    @GetMapping("/js/calculation.js")
    public String initialJS() {
        return "calculation/calculation.js";
    }
}
