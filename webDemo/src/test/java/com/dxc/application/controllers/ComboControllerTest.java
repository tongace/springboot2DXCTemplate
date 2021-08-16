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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComboControllerTest {
    private TestRestTemplate restTemplate;

    @Autowired
    public ComboControllerTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    @DisplayName("Test get Gim Type Combo must have 3 items")
    void testGimTypeComboMustHave3Items() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo",RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(),new TypeReference<List<Combo>>(){});
        assertEquals(3,gimTypeCombo.size());
    }

    @Test
    @DisplayName("Test get Gim Type Combo must have  items ACTIVE_FLAG ")
    void testGimTypeComboMustHaveItemsActiveFlag() {
        ResponseEntity<RestJsonData> response = restTemplate.getForEntity("/combo/gimtypecombo",RestJsonData.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Combo> gimTypeCombo = mapper.convertValue(response.getBody().getData(),new TypeReference<List<Combo>>(){});
        assertTrue(gimTypeCombo.stream().anyMatch(item -> "ACTIVE_FLAG".equals(item.getName())));
    }

    @Test
    void getActiveFlagCombo() {
    }
}