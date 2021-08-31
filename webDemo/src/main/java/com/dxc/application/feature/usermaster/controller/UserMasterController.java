package com.dxc.application.feature.usermaster.controller;

import com.dxc.application.constants.MessagesConstants;
import com.dxc.application.exceptions.ApplicationException;
import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.feature.usermaster.data.database.model.User;
import com.dxc.application.feature.usermaster.data.database.model.UserSearchCriteria;
import com.dxc.application.feature.usermaster.dto.InsertUserDTO;
import com.dxc.application.feature.usermaster.dto.SearchUserDTO;
import com.dxc.application.feature.usermaster.dto.UpdateUserDTO;
import com.dxc.application.feature.usermaster.dto.UserResultDTO;
import com.dxc.application.feature.usermaster.service.UserMasterService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usermaster")
@Slf4j
public class UserMasterController {

    private final UserMasterService userMasterService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserMasterController(UserMasterService userMasterService, ModelMapper modelMapper) {
        this.userMasterService = userMasterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String initialHTML(Model model) {
        return "usermaster/usermaster.html";
    }

    @GetMapping("/js/usermaster.js")
    public String initialJS(Model model) {
        return "usermaster/usermaster.js";
    }

    @GetMapping("/js/usermaster-call-api.js")
    public String initialJSApi(Model model) {
        return "usermaster/usermaster-call-api.js";
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    @SneakyThrows
    RestJsonData<List<UserResultDTO>> getUser(@RequestBody SearchUserDTO input, HttpServletRequest request) {
        RestJsonData<List<UserResultDTO>> returnData = new RestJsonData<>();

        UserSearchCriteria criteria = modelMapper.map(input, UserSearchCriteria.class);
        List<UserResultDTO> userMasterList = userMasterService.searchUser(criteria);

        if (userMasterList.isEmpty()) {
            throw new ApplicationException(MessagesConstants.ERROR_MESSAGE_DATA_NOT_FOUND, null);
        }
        returnData.setData(userMasterList);
        return returnData;
    }

    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    RestJsonData<String> insert(@RequestPart("userPic") MultipartFile userPic, @RequestPart("userDTO") InsertUserDTO input) {
        RestJsonData<String> returnData = new RestJsonData<>();
        User criteria = modelMapper.map(input, User.class);
        criteria.setCreatedBy("csamphao");
        criteria.setModifiedBy("csamphao");
        int saveRowCount = userMasterService.insertUser(criteria, userPic);
        returnData.setRowCount(new BigDecimal(saveRowCount));
        return returnData;
    }

    @PatchMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    RestJsonData<String> updateUser(@RequestPart("userPic") MultipartFile userPic, @RequestPart("userDTO") UpdateUserDTO input) {
        RestJsonData<String> returnData = new RestJsonData<>();
        User criteria = modelMapper.map(input, User.class);
        criteria.setModifiedBy("csamphao");
        int saveRowCount = userMasterService.updateUser(criteria, userPic);
        returnData.setRowCount(new BigDecimal(saveRowCount));
        return returnData;
    }

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RestJsonData<String> deleteUser(@RequestBody List<UpdateUserDTO> userDTOList, HttpServletRequest request) {
        RestJsonData<String> returnData = new RestJsonData<>();
        List<User> users = userDTOList.stream().map(dto -> modelMapper.map(dto, User.class)).collect(Collectors.toList());
        int saveRowCount = userMasterService.deleteUser(users);
        returnData.setRowCount(new BigDecimal(saveRowCount));
        return returnData;
    }
}
