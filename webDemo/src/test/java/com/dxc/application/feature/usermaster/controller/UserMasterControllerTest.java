package com.dxc.application.feature.usermaster.controller;

import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.feature.usermaster.data.database.UserMasterMapper;
import com.dxc.application.feature.usermaster.data.database.model.User;
import com.dxc.application.feature.usermaster.dto.InsertUserDTO;
import com.dxc.application.feature.usermaster.dto.SearchUserDTO;

import com.dxc.application.feature.usermaster.dto.UpdateUserDTO;
import com.dxc.application.feature.usermaster.dto.UserResultDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource",transactionManager = "mybastistx")
class UserMasterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserMasterMapper userMasterMapper;

    @Test
    @DisplayName("Test search data not found")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getNotFoundUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchCitizenId("0");
        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        RestJsonData resultData = mapper.convertValue(response.getBody(),new TypeReference<RestJsonData>(){});

        assertNull(resultData.getData());
        assertTrue("MAPP0006AERR: No data found".equalsIgnoreCase(resultData.getMessage()));
    }

    @Test
    @DisplayName("Test search data found by no search criteria")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertNotNull(userMasterList);
        assertEquals(3,userMasterList.size());
    }

    @Test
    @DisplayName("Test search data found with criteria citizenId , first name , last name")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaAll() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchCitizenId("1111111111111");
        userCriteria.setSearchFirstName("Prayut");
        userCriteria.setSearchLastName("Jarnocha");


        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> "1111111111111".equals(item.getCitizenId())));
    }


    @Test
    @DisplayName("Test search data found with criteria citizenId")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaCitizenId() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchCitizenId("1111111111111");

        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> ("1111111111111").equals(item.getCitizenId())));
    }

    @Test
    @DisplayName("Test search data found with criteria first name")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaFirstName() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchFirstName("Suwimon");

        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> "Suwimon".equals(item.getFirstName() )));
    }

    @Test
    @DisplayName("Test search data found with criteria last name")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaLastName() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchLastName("Fcuk");

        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> "Fcuk".equals(item.getLastName())));
    }

    @Test
    @DisplayName("Test search data found with criteria first name")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaFirstNameWildcard() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchFirstName("Suwi*");

        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> "Suwimon".equals(item.getFirstName() )));
    }

    @Test
    @DisplayName("Test search data found with criteria last name")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getFoundUserWithCriteriaLastNameWildcard() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SearchUserDTO userCriteria = new SearchUserDTO();
        userCriteria.setSearchLastName("Fc*");

        HttpEntity<SearchUserDTO> requestEntity = new HttpEntity<>(userCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/usermaster/user",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResultDTO> userMasterList = mapper.convertValue(response.getBody().getData(),new TypeReference<List<UserResultDTO>>(){});

        assertEquals(1,userMasterList.size());
        assertTrue(userMasterList.stream().anyMatch(item -> "Fcuk".equals(item.getLastName())));
    }

    @Test
    @DisplayName("Test add user ")
    @SneakyThrows
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void insertUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentLength(88491);

        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        HttpHeaders jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
        InsertUserDTO userMaster = new InsertUserDTO();
        userMaster.setCitizenId("4444444444444");
        userMaster.setFirstName("udom");
        userMaster.setLastName("no one");
        userMaster.setAddress("New york");
        userMaster.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("20/10/1992"));
        userMaster.setPictureId(0);
        userMaster.setCreatedBy("csamphao");
        userMaster.setModifiedBy("csamphao");
        HttpEntity<InsertUserDTO> jsonReqEntity = new HttpEntity<>(userMaster, jsonHeader);
        body.set("userDTO",jsonReqEntity);


        HttpHeaders fileHeader = new HttpHeaders();
        fileHeader.setContentType(MediaType.IMAGE_JPEG);
        fileHeader.setContentLength(88006);
        ClassPathResource resource = new ClassPathResource("img/image002.jpg");
        HttpEntity<ClassPathResource> fileReqEntity = new HttpEntity<>(resource, fileHeader);
        body.set("userPic",fileReqEntity);

        HttpEntity<MultiValueMap<String,Object>> requestObj = new HttpEntity<>(body,headers);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.PUT,requestObj,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        BigDecimal rowInsert = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,rowInsert.intValue());
    }

    @Test
    @DisplayName("Test add duplicate user ")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void insertDuplicateUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        InsertUserDTO userMaster = new InsertUserDTO();
        userMaster.setCitizenId("2222222222222");
        userMaster.setFirstName("udom");
        userMaster.setLastName("no one");
        userMaster.setAddress("New york");
        try {
            userMaster.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("20/10/1992"));
        } catch (ParseException e) {
            log.debug("set birth date error : {} ",e.getMessage());
        }

        userMaster.setPictureId(0);
        userMaster.setCreatedBy("csamphao");
        userMaster.setModifiedBy("csamphao");

        HttpEntity<InsertUserDTO> requestEntity = new HttpEntity<>(userMaster, headers);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.PUT,requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        RestJsonData resultData = mapper.convertValue(response.getBody(),new TypeReference<RestJsonData>(){});
        log.info("mess:{}",resultData.getMessage());
        assertTrue("MCOM0004AERR: Data already exist in database.".equalsIgnoreCase(resultData.getMessage()));
    }

    @Test
    @DisplayName("Test update user")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void updateUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UpdateUserDTO userMaster = new UpdateUserDTO();
        UserResultDTO oldUserMaster = userMasterMapper.findUserPrimaryKey("1111111111111");

        userMaster.setCitizenId(oldUserMaster.getCitizenId());
        userMaster.setFirstName(oldUserMaster.getFirstName());
        userMaster.setLastName("Change Last Name");
        userMaster.setAddress(oldUserMaster.getAddress());
        userMaster.setDateOfBirth(oldUserMaster.getDateOfBirth());
        userMaster.setPictureId(oldUserMaster.getPictureId());
        userMaster.setModifiedDt(oldUserMaster.getModifiedDt());
        userMaster.setModifiedBy("csamphao");

        HttpEntity<UpdateUserDTO> requestEntity = new HttpEntity<>(userMaster, headers);


        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.PATCH,requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        BigDecimal rowUpdate = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,rowUpdate.intValue());
    }

    @Test
    @DisplayName("Test update user that has been modified by another user")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void updateUserConcurrent() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UpdateUserDTO userMaster = new UpdateUserDTO();
        UserResultDTO oldUserMaster = userMasterMapper.findUserPrimaryKey("1111111111111");

        userMaster.setCitizenId(oldUserMaster.getCitizenId());
        userMaster.setFirstName(oldUserMaster.getFirstName());
        userMaster.setLastName("Change Last Name");
        userMaster.setAddress(oldUserMaster.getAddress());
        userMaster.setDateOfBirth(oldUserMaster.getDateOfBirth());
        userMaster.setPictureId(oldUserMaster.getPictureId());
        try {
            userMaster.setModifiedDt(new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2021"));
        } catch (ParseException e) {
            log.debug("setModifiedDt error : {} ",e.getMessage());
        }
        userMaster.setModifiedBy("csamphao");

        HttpEntity<UpdateUserDTO> requestEntity = new HttpEntity<>(userMaster, headers);


        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.PATCH,requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        RestJsonData resultData = mapper.convertValue(response.getBody(),new TypeReference<RestJsonData>(){});

        assertEquals(0,resultData.getRowCount().intValue());
        log.info("mess:{}",resultData.getMessage());
        //assertTrue("MBX00009AERR: Operation cannot be completed. Data has been modified by another user.".equalsIgnoreCase(resultData.getMessage()));
    }

    @Test
    @DisplayName("Test delete user")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteUser() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        UpdateUserDTO userMaster = new UpdateUserDTO();
        UserResultDTO oldUserMaster = userMasterMapper.findUserPrimaryKey("3333333333333");

        userMaster.setCitizenId(oldUserMaster.getCitizenId());
        userMaster.setModifiedDt(oldUserMaster.getModifiedDt());

        UpdateUserDTO[] deleteUserArray = new UpdateUserDTO[]{userMaster};

        HttpEntity<UpdateUserDTO[]> requestEntity = new HttpEntity<>(deleteUserArray, headers);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.DELETE,requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        BigDecimal rowUpdate = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,rowUpdate.intValue());
    }

    @Test
    @DisplayName("Test delete user that has been modified by another user")
    @Sql(value = {"/testdata/UserMasterControllerTest/clearUserData.sql","/testdata/UserMasterControllerTest/testUserData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteUserConcurrent() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        UpdateUserDTO userMaster = new UpdateUserDTO();
        UserResultDTO oldUserMaster = userMasterMapper.findUserPrimaryKey("3333333333333");

        //Delete before test delete
        User deleteUser = new User();
        deleteUser.setCitizenId(oldUserMaster.getCitizenId());
        userMasterMapper.deleteUser(deleteUser);

        userMaster.setCitizenId(oldUserMaster.getCitizenId());
        userMaster.setModifiedDt(oldUserMaster.getModifiedDt());

        UpdateUserDTO[] deleteUserArray = new UpdateUserDTO[]{userMaster};

        HttpEntity<UpdateUserDTO[]> requestEntity = new HttpEntity<>(deleteUserArray, headers);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/usermaster/user", HttpMethod.DELETE,requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        RestJsonData resultData = mapper.convertValue(response.getBody(),new TypeReference<RestJsonData>(){});

        assertEquals(0,resultData.getRowCount().intValue());
        //assertTrue("MBX00009AERR: Operation cannot be completed. Data has been modified by another user.".equalsIgnoreCase(resultData.getMessage()));
    }
}