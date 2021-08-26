package com.dxc.application.controllers;

import com.dxc.application.feature.common.data.database.model.Combo;
import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.feature.gimmaster.dto.SearchGimHeaderDTO;
import com.dxc.application.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource", transactionManager = "mybastistx")
@Slf4j
class ComboControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test get Gim Type Combo must have 3 items")
    @Sql(value = {"/testdata/ComboControllerTest/testGimTypeComboMustHave3Items.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGimTypeComboMustHave3Items() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo", RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(), new TypeReference<List<Combo>>() {
        });
        assertEquals(3, gimTypeCombo.size());
    }

    @Test
    @DisplayName("Test get Gim Type Combo must have  items ACTIVE_FLAG ")
    @Sql(value = {"/testdata/ComboControllerTest/testGimTypeComboMustHaveItemsActiveFlag.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGimTypeComboMustHaveItemsActiveFlag() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo", RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(), new TypeReference<List<Combo>>() {
        });
        assertTrue(gimTypeCombo.stream().anyMatch(item -> "ACTIVE_FLAG".equals(item.getName())));
    }

    @Test
    @DisplayName("Test get Active flag Combo must have  2 active items ")
    @Sql(value = {"/testdata/ComboControllerTest/getActiveFlagComboMustHave2ActiveItems.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void getActiveFlagComboMustHave2ActiveItems() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/activeflagcombo", RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> activeCombo = mapper.convertValue(response.getBody().getData(), new TypeReference<List<Combo>>() {
        });
        assertEquals(2, activeCombo.size());
    }

    @Test
    @DisplayName("search gim header with search gim type = null, search gim desc = null and active flag = all")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDefaultCriteria.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithDefaultCriteria() {
        SearchGimHeaderDTO requestObj = new SearchGimHeaderDTO();
        requestObj.setSearchGimTypes(new String[]{"ACTIVE_FLAG", "TEST_GIM"});
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader", requestObj, RestJsonData.class);
        log.info("response raw data >>>>>>>>>>>>>>>> {}", JsonUtil.toJsonString(response));
        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> result = mapper.convertValue(response.getBody().getData(), new TypeReference<List<GimHeader>>() {
        });
        assertEquals(2, result.size());
        String message = mapper.convertValue(response.getBody().getMessage(), new TypeReference<String>() {
        });
        assertNull(message, "message should be null");
    }

    @Test
    @DisplayName("search gim header with search gim type with No Data Found")
    @Sql(value = {"/testdata/ComboControllerTest/searchGimHeaderWithDataNotFound.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void searchGimHeaderWithDataNotFound() {
        SearchGimHeaderDTO requestObj = new SearchGimHeaderDTO();
        requestObj.setSearchGimDesc("GGGGGGG");
        requestObj.setSearchActiveFlag("ALL");
        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader", requestObj, RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.convertValue(response.getBody().getMessage(), new TypeReference<String>() {
        });
        assertEquals("MAPP0006AERR: No data found", message);
        assertNull(response.getBody().getData(), "data should be null");
    }

    @Test
    @DisplayName("Load Gim Page")
    void loadGimPage() {
        ResponseEntity<String> response = restTemplate.getForEntity("/gimmaster", String.class);
        assertTrue(response.getBody().contains("</html>"), "found end of html");
    }

    @Test
    @DisplayName("Load Gim Api js")
    void loadGimAPIJS() {
        ResponseEntity<String> response = restTemplate.getForEntity("/gimmaster/js/gimmaster-call-api.js", String.class);
        assertTrue(response.getBody().contains("//]]'>"), "found end of js");
    }

    @Test
    @DisplayName("Load Gim js")
    void loadGimJS() {
        ResponseEntity<String> response = restTemplate.getForEntity("/gimmaster/js/gimmaster.js", String.class);
        assertTrue(response.getBody().contains("//]]'>"), "found end of js");
    }
}