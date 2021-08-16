package com.dxc.application.controllers;

import com.dxc.application.model.Combo;
import com.dxc.application.model.RestJsonData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource",transactionManager = "mybastistx")
class ComboControllerTest {
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
}