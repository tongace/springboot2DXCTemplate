package com.dxc.application.controllers;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.model.Combo;
import com.dxc.application.model.GimDetail;
import com.dxc.application.model.GimHeader;
import com.dxc.application.model.RestJsonData;
import com.dxc.application.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource",transactionManager = "mybastistx")
@Slf4j
class GimMastersControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test get Gim Type Combo must have 3 items")
    @Sql(value = {"/testdata/ComboControllerTest/testGimTypeComboMustHave3Items.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGimTypeComboMustHave3Items() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo",RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(),new TypeReference<List<Combo>>(){});
        assertEquals(3,gimTypeCombo.size());
    }

    @Test
    @DisplayName("Test get Gim Type Combo must have  items ACTIVE_FLAG ")
    @Sql(value = {"/testdata/ComboControllerTest/testGimTypeComboMustHaveItemsActiveFlag.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGimTypeComboMustHaveItemsActiveFlag() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo",RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(),new TypeReference<List<Combo>>(){});
        assertTrue(gimTypeCombo.stream().anyMatch(item -> "ACTIVE_FLAG".equals(item.getName())));
    }

    @Test
    @DisplayName("Test get Active flag Combo must have  2 active items ")
    @Sql(value = {"/testdata/ComboControllerTest/getActiveFlagComboMustHave2ActiveItems.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getActiveFlagComboMustHave2ActiveItems() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/activeflagcombo",RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> activeCombo = mapper.convertValue(response.getBody().getData(),new TypeReference<List<Combo>>(){});
        assertEquals(2,activeCombo.size());
    }

    @Test
    @DisplayName("search gim header with search gim type = null, search gim desc = null and active flag = all")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithDefaultCriteria() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimTypes(new String[]{"ACTIVE_FLAG", "TEST_GIM"});
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(2,result.size());
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertNull(message,"message should be null");
    }

    @Test
    @DisplayName("search gim header with search gim type with No Data Found")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDataNotFound.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithDataNotFound() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimDesc("GGGGGGG");
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertEquals("MAPP0006AERR: No data found",message);
        assertNull(response.getBody().getData(),"data should be null");
    }











    @Test
    @DisplayName("search gim header with search gim type = STATUS, search gim desc = null , active flag = all")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithGimType() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimTypes(new String[]{"STATUS"});
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(1,result.size());

    }


    @Test
    @DisplayName("search gim header with search  gim desc = ddddd  , gim type = null , active flag = all")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithGimDesc() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimDesc("ddddd");
        requestObj.setSearchGimTypes(null);
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(1,result.size());

    }

    @Test
    @DisplayName("search gim header with search  gim desc and wildcard = Prod*  , gim type = null , active flag = all")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithGimDescWildCard() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimDesc("Prod*");
        requestObj.setSearchGimTypes(null);
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(1,result.size());

    }

    @Test
    @DisplayName("search gim header with search  active flag = Y , gim desc  = null , gim type = null ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithActiveFlag() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimDesc("");
        requestObj.setSearchGimTypes(null);
        requestObj.setSearchActiveFlag("Y");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(3,result.size());

    }

    @Test
    @DisplayName("search gim header with search  active flag = Y , gim desc  = Production Release Status Puay , gim type = STATUS ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithAllField() {
        GimHeader requestObj = new GimHeader();
        requestObj.setSearchGimDesc("Production Release Status Puay");
        requestObj.setSearchGimTypes(new String[]{"STATUS"});
        requestObj.setSearchActiveFlag("Y");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(1,result.size());

    }


    @Test
    @DisplayName("add gim header ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void addGimHeader() {
        GimHeader requestObj = new GimHeader();
        requestObj.setGimType("GIM_TYPE_EK");
        requestObj.setGimDesc("Gim Dest EK");
        requestObj.setCdLength( new BigDecimal(10));
        requestObj.setActiveFlag("Y");
        requestObj.setCreatedBy("ekk");
        requestObj.setModifiedBy("ekk");
        requestObj.setField1Label("not used");
        requestObj.setField2Label("not used");
        requestObj.setField3Label("not used");
        requestObj.setMode(AppConstants.MODE_ADD);
        HttpEntity<GimHeader> req = new HttpEntity<>(requestObj,null);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/gimmaster/gimheader", HttpMethod.PUT,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }

    @Test
    @DisplayName("update gim header ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void updateGimHeader() {
        GimHeader requestObj = new GimHeader();
        requestObj.setGimType("ACTIVE_FLAG");
        requestObj.setGimDesc("Gim Dest EK");
        requestObj.setCdLength( new BigDecimal(10));
        requestObj.setActiveFlag("Y");
        requestObj.setCreatedBy("ekk");
        requestObj.setModifiedBy("ekk");
        requestObj.setField1Label("update1");
        requestObj.setField2Label("update12");
        requestObj.setField3Label("update3");
        requestObj.setMode(AppConstants.MODE_EDIT);
        String dateTime="20211201 10:37:42";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try {
            requestObj.setModifiedDt(formatter. parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpEntity<GimHeader> req = new HttpEntity<>(requestObj,null);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/gimmaster/gimheader", HttpMethod.PUT,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());

    }

    @Test
    @DisplayName("add gim detail ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void addGimDetail() {
        GimDetail gimDetail = new GimDetail();
        gimDetail.setGimType("STATUS");
        gimDetail.setActiveFlag("Y");
        gimDetail.setGimCd("Cd1");
        gimDetail.setCreatedBy("ek");
        gimDetail.setMode(AppConstants.MODE_ADD);
        gimDetail.setCreatedDt(new Date());
        gimDetail.setModifiedBy("ek");
        gimDetail.setModifiedDt(new Date());
        gimDetail.setField1("not used");
        gimDetail.setField1("not used");
        gimDetail.setField1("not used");
        gimDetail.setGimValue("gimval");

        HttpEntity<GimDetail> req = new HttpEntity<>(gimDetail,null);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/gimmaster/gimdetail", HttpMethod.PUT,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }

    @Test
    @DisplayName("search gim detail by Gim Type Hyerlink ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimDetailByGimTypeHyperlink() {
        GimDetail requestObj = new GimDetail();
        requestObj.setGimType("ACTIVE_FLAG");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimdetail",requestObj,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimDetail> result = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimDetail>>(){});
        assertEquals(2,result.size());

    }

    @Test
    @DisplayName("search gim detail,Case No Data Found")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimDetailWithDataNotFound() {
        GimDetail requestObj = new GimDetail();
        requestObj.setGimType("xx");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimdetail",requestObj,RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(),new TypeReference<String>(){});
        assertEquals("MAPP0006AERR: No data found",message);
        assertNull(response.getBody().getData(),"data should be null");
    }


    @Test
    @DisplayName("delete gim detail ")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteGimDetail() {
        GimDetail gimDetail = new GimDetail();
        gimDetail.setGimType("ACTIVE_FLAG");
        gimDetail.setGimCd("N");
        String dateTime="20210804 10:43:35";
        DateFormat formatter=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        try {
            gimDetail.setModifiedDt(formatter. parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GimDetail gimArray[] = new GimDetail[]{gimDetail};
        HttpEntity<GimDetail[]> req = new HttpEntity<>(gimArray,null);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/gimmaster/gimdetail", HttpMethod.DELETE,req,RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}",JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal result = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,result.intValue());
    }



}