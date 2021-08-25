package com.dxc.application.controllers;

import com.dxc.application.model.RestJsonData;
import com.dxc.application.model.User;
import com.dxc.application.model.UserMaster;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    @GetMapping(value = "/usermaster", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find UserMaster",
            notes = "Provide an UserMaster object to look up specific user from the user master")
    public @ResponseBody
    @SneakyThrows
    RestJsonData<List<UserMaster>> getUser(@ApiParam(value = "UserMaster object for the user you need to retrieve", required = true) @RequestBody UserMaster input, HttpServletRequest request) {
        RestJsonData<List<UserMaster>> returnData = new RestJsonData<>();
        return returnData;
    }
}
