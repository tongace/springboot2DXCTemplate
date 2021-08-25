package com.dxc.application.feature.common.service;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.feature.common.data.database.model.Combo;
import com.dxc.application.feature.common.data.database.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
