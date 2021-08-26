package com.dxc.application.feature.common.service;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(value = "mybastistx")
class CommonServiceTest {
    @Autowired
    CommonService commonService;

    @Test
    @DisplayName("Insert Create Attach file ")
    @SneakyThrows
    void insertAttachedFile() {
        File file = ResourceUtils.getFile("classpath:img/image002.jpg");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "image/jpeg", IOUtils.toByteArray(input));
        Integer fileID = commonService.insertAttachedFile(multipartFile, "unitTest");
        assertNotNull(fileID, String.format("file ID is %s", fileID));
    }
}