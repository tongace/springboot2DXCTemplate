package com.dxc.application.feature.personsmaster.controller;

import com.dxc.application.feature.personsmaster.dto.*;
import com.dxc.application.model.Person;
import com.dxc.application.feature.common.dto.RestJsonData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/personsmaster")
public class PersonsMasterController {
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

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<List<PersonsResultDTO>> searchPerson(@RequestBody SearchPersonsDTO input, HttpServletRequest request) {
        RestJsonData<List<PersonsResultDTO>> returnData = new RestJsonData<>();
        return returnData;
    }

    @PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RestJsonData<String> insertPerson(@RequestBody InsertPersonsDTO input, HttpServletRequest request) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }

    @PatchMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RestJsonData<String> updatePerson(@RequestBody UpdatePersonsDTO input, HttpServletRequest request) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }

    @DeleteMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RestJsonData<String> deletePerson(@RequestBody DeletePersonsDTO input, HttpServletRequest request) {
        RestJsonData<String> returnData = new RestJsonData<>();
        return returnData;
    }


}
