package com.dxc.application.feature.common.service;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.feature.common.data.database.CommonMapper;
import com.dxc.application.feature.common.data.database.model.AttachedFile;
import com.dxc.application.feature.common.data.database.model.Combo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final CommonMapper commonMapper;

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
    public List<Combo> getAutoCompleteCombo(String messageCode) {
        List<Combo> results = new ArrayList<Combo>(){{
            add(new Combo("BANK01","BANK01"));
            add(new Combo("BANK02","BANK02"));
            add(new Combo("EAK01","EAK01"));
            add(new Combo("EAK02","EAK02"));
        }};
        return results.stream().filter(combo -> combo.getValue().startsWith(messageCode)).collect(Collectors.toList());
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public AttachedFile getAttachedFile(Integer id) {
        return commonMapper.getAttachedFileById(id);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    @SneakyThrows
    public Integer insertAttachedFile(MultipartFile multipartFile, String uploadedBy) {
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFileName(multipartFile.getOriginalFilename());
        attachedFile.setFileContentType(multipartFile.getContentType());
        attachedFile.setFileData(multipartFile.getBytes());
        attachedFile.setCreatedBy(uploadedBy);
        commonMapper.insertAttachedFiled(attachedFile);
        return attachedFile.getId();
    }
    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    @SneakyThrows
    public Integer deleteAttachedFileById(Integer fileId) {
        return commonMapper.deleteAttachedFileById(fileId);
    }
}
