package com.dxc.application.feature.common.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.util.ResourceUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SqlConfig(dataSource = "myBatisDataSource", transactionManager = "mybastistx")
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommonControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("test get attach file by id")
    @SneakyThrows
    void getAttachedFile() {
        ResponseEntity<byte[]> response = restTemplate.exchange("/common/file/3/", HttpMethod.GET, null, byte[].class);
        assertEquals("filename=image002.jpg", response.getHeaders().get("Content-Disposition").get(0));
        assertNotNull(response.getBody());
        File file = ResourceUtils.getFile("classpath:img/image002.jpg");
        assertEquals(file.length(), response.getBody().length, "file size");
    }
}