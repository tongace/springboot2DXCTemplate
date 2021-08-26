package com.dxc.application.feature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.dxc.application.model.RestJsonData;
import com.dxc.application.model.User;
import com.dxc.application.services.GimMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @GetMapping("")
    public String home(HttpServletRequest request) {
        return "views/home.html";
    }

    @GetMapping("/js/home.js")
    public String js() {
        return "js/home.js";
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        return "test";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    RestJsonData<BigDecimal> upload(@RequestPart("userPic") MultipartFile userPic,@RequestPart("userModel") User userModel){
        log.debug("upload File Name >>>> {}",userPic.getOriginalFilename());
        log.debug("userModel >>>> {}",userModel);
        return new RestJsonData<>();
    }
}
