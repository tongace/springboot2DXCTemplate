package com.dxc.application.feature.personsmaster.controller;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.feature.personsmaster.dto.*;
import com.dxc.application.feature.personsmaster.dto.SearchPersonsDTO;
import com.dxc.application.model.*;
import com.dxc.application.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.dxc.application.feature.common.dto.RestJsonData ;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource",transactionManager = "mybastistx")
@Slf4j
class PersonsMasterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("search Person with Citizen Id = 22222, First Name = null , Last Name = null ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithCitizenId() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchCitizenId("22222");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());

    }

    @Test
    @DisplayName("search Person with  First Name = firstName1 ,Citizen Id = null , Last Name = null ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithFirstName() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchFirstName("firstName1");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }

    @Test
    @DisplayName("search Person with  First Name = first* ,Citizen Id = null , Last Name = null ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithFirstNameAndWildCard() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchFirstName("first*");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }


    @Test
    @DisplayName("search Person with Last Name = lastName1 , First Name = null  ,Citizen Id = null  ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithLastName() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchLastName("lastName1");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }

    @Test
    @DisplayName("search Person with Last Name = last* , First Name = null  ,Citizen Id = null  ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithLastNameAndWildCard() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchLastName("last*");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }


    @Test
    @DisplayName("search Person with Citizen Id = 22222  , Last Name = lastName, First Name = null ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonWithCitizenIdAndLastName() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchCitizenId("22222");
        requestObj.setSearchLastName("lastName1");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }

    @Test
    @DisplayName("search Person with Citizen Id = 22222  , Last Name = lastName1 , First Name = firstname1 ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonAllSearchCriteria() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchCitizenId("22222");
        requestObj.setSearchFirstName("firstname1");
        requestObj.setSearchLastName("lastName1");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<PersonsResultDTO> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<PersonsResultDTO>>(){});
        assertEquals(1,result.size());
    }

    @Test
    @DisplayName("search Person with Citizen Id = xxxxx  , Last Name = null , First Name = null ")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchPersonCaseNoDataFound() {
        SearchPersonsDTO requestObj = new SearchPersonsDTO();
        requestObj.setSearchCitizenId("xxxxx");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/personsmaster/person",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertEquals("MAPP0006AERR: No data found",message);
        assertNull(response.getBody().getData(),"data should be null");
    }

    @Test
    @DisplayName("insert Person ")
    void addPerson() {
        InsertPersonsDTO requestObj = new InsertPersonsDTO();
        requestObj.setCitizenId("1111");
        requestObj.setFirstName("firstName1");
        requestObj.setLastName("lastName1");
        String birthDate ="2001201";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
        try {
            requestObj.setBirthDate(formatter. parse(birthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        requestObj.setFileName("Pic1.jpg");
        HttpEntity<InsertPersonsDTO> req = new HttpEntity<>(requestObj,null);
        ResponseEntity<RestJsonData> response = restTemplate.exchange("/personsmaster/person", HttpMethod.PUT,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }

    @Test
    @DisplayName("insert Person Case duplicated")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void addPersonCaseDuplicated() {
        InsertPersonsDTO requestObj = new InsertPersonsDTO();
        requestObj.setCitizenId("1111");
        requestObj.setFirstName("firstName1");
        requestObj.setLastName("lastName1");
        String birthDate ="2001201";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
        try {
            requestObj.setBirthDate(formatter. parse(birthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        requestObj.setFileName("Pic1.jpg");
        HttpEntity<InsertPersonsDTO> req = new HttpEntity<>(requestObj,null);
        ResponseEntity<RestJsonData> response = restTemplate.exchange("/personsmaster/person", HttpMethod.PUT,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertEquals("MAPP0010AERR: Duplicate data",message);
    }


    @Test
    @DisplayName("update Person")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @SneakyThrows
    void updatePerson() {
        UpdatePersonsDTO requestObj = new UpdatePersonsDTO();
        requestObj.setCitizenId("1111");
        requestObj.setFirstName("firstNameEdit");
        requestObj.setLastName("lastNameEdit");
        String birthDate ="2001201";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
        requestObj.setBirthDate(formatter. parse(birthDate));

        requestObj.setFileName("Pic1.jpg");
        String modifiedDate ="20210101 03:23:50";
        formatter=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        requestObj.setModifiedDt(formatter.parse(modifiedDate));
        HttpEntity<UpdatePersonsDTO> req = new HttpEntity<>(requestObj,null);
        ResponseEntity<RestJsonData> response = restTemplate.exchange("/personsmaster/person", HttpMethod.PATCH,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }

    @Test
    @DisplayName("update Person (Change by Another )")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @SneakyThrows
    void updatePersonChangeByAnother() {
        UpdatePersonsDTO requestObj = new UpdatePersonsDTO();
        requestObj.setCitizenId("1111");
        requestObj.setFirstName("firstNameEdit");
        requestObj.setLastName("lastNameEdit");
        String birthDate ="2001201";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd");
        requestObj.setBirthDate(formatter. parse(birthDate));

        requestObj.setFileName("Pic1.jpg");
        String modifiedDate ="20210202 03:23:50";
        formatter=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        requestObj.setModifiedDt(formatter.parse(modifiedDate));
        HttpEntity<UpdatePersonsDTO> req = new HttpEntity<>(requestObj,null);
        ResponseEntity<RestJsonData> response = restTemplate.exchange("/personsmaster/person", HttpMethod.PATCH,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertEquals("MBX00009AERR: Operation cannot be completed. Data has been modified by another user.",message);

    }


    @Test
    @DisplayName("delete Person")
    @Sql(value = {"/testdata/ComboControllerTest/testPersons.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteGimDetail() {

        DeletePersonsDTO requestObj = new DeletePersonsDTO();
        String[] citizenId = new String[]{"22222"};
        requestObj.setCitizenId(citizenId);
        HttpEntity<DeletePersonsDTO> req = new HttpEntity<>(requestObj,null);
        ResponseEntity<RestJsonData> response = restTemplate.exchange("/personsmaster/person", HttpMethod.DELETE,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }


}