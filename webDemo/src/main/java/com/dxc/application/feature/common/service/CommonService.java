package com.dxc.application.feature.common.service;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.feature.common.data.database.CommonMapper;
import com.dxc.application.feature.common.data.database.model.AttachedFile;
import com.dxc.application.feature.common.data.database.model.Combo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CommonService {
    private CommonMapper commonMapper;

    @Autowired
    public CommonService(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public Date getDBServerTime() {
        return commonMapper.getDBDateTime();
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<Combo> getGimTypeCombo() {
        return commonMapper.getGimTypeCombo();
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<Combo> getActiveFlagCombo() {
        return commonMapper.getActiveFlagCombo(AppConstants.ACTIVE_FLAG_ACTIVE);
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public AttachedFile getAttachedFile(BigDecimal id) {
        return commonMapper.getAttachedFileById(id);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    @SneakyThrows
    public Integer insertAttachedFile(MultipartFile multipartFile, String uploadedBy){
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFileName(multipartFile.getOriginalFilename());
        attachedFile.setFileContentType(multipartFile.getContentType());
        attachedFile.setFileData(multipartFile.getBytes());
        attachedFile.setCreatedBy(uploadedBy);
        return commonMapper.insertAttachedFiled(attachedFile);
    }
}
