package com.dxc.application.feature.common.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.application.feature.common.data.database.model.AttachedFile;
import com.dxc.application.feature.common.service.CommonService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    private CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/dbservertime")
    @ResponseBody
    public Date home(HttpServletRequest request) {
        log.info("DB Server Time request!!");
        return commonService.getDBServerTime();
    }

    @GetMapping(value = "file/{id}")
    @SneakyThrows
    public void downloadAttachedFile(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") BigDecimal id) {
        AttachedFile attachedFile = commonService.getAttachedFile(id);
        String mimeType = URLConnection.guessContentTypeFromName(attachedFile.getFileName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(attachedFile.getFileName(), "UTF-8"));
        response.setContentLength(attachedFile.getFileData().length);
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(attachedFile.getFileData()));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
