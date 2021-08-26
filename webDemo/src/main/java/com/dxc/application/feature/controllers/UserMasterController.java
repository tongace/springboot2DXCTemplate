package com.dxc.application.feature.controllers;

import com.dxc.application.feature.dto.NewUserMaster;
import com.dxc.application.feature.dto.SearchUserMaster;
import com.dxc.application.feature.dto.UpdatedUserMaster;
import com.dxc.application.model.RestJsonData;
import com.dxc.application.model.UserMaster;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping(value = "/usermasters", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<List<UserMaster>> getUsers(@RequestBody SearchUserMaster input) {
        RestJsonData<List<UserMaster>> returnData = new RestJsonData<>();
        return returnData;
    }

    @GetMapping(value = "/usermaster", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<List<UserMaster>> getUser(@PathVariable("citizenId") BigDecimal citizenId) {
        RestJsonData<List<UserMaster>> returnData = new RestJsonData<>();
        return returnData;
    }

    @PostMapping(value = "/usermaster", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    @Operation(description = "Insert a new user")
    RestJsonData<String> insertUser(@RequestBody NewUserMaster input) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }

    @DeleteMapping(value = "/usermaster", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<String> deleteUser(@PathVariable("citizenId") BigDecimal citizenId) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }
    
    @PatchMapping(value = "/usermaster", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<String> updateUser(@RequestBody UpdatedUserMaster input) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }
}
