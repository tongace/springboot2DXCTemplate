package com.dxc.application.feature.controllers;

import com.dxc.application.model.RestJsonData;
import com.dxc.application.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/api/v1/user/")
public class UserController {

    @GetMapping("{id}")
    @ResponseBody
    public RestJsonData<User> getUserById(@PathVariable("id") BigDecimal id){
        RestJsonData<User> retJson = new RestJsonData<>();
        return retJson;
    }

}
