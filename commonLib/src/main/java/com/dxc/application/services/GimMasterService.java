package com.dxc.application.services;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.model.GimDetail;
import com.dxc.application.model.GimHeader;
import com.dxc.application.mybatis.mapper.GIMMasterMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GimMasterService {

    private GIMMasterMapper gimHeaderMapper;

    @Autowired
    GimMasterService(GIMMasterMapper gimHeaderMapper) {
        this.gimHeaderMapper = gimHeaderMapper;
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<GimHeader> getGimHeader(GimHeader criteria) {
        return gimHeaderMapper.findGimHeader(criteria);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int saveGimHeader(GimHeader gimData) {
        if (StringUtils.equalsIgnoreCase(gimData.getMode(), AppConstants.MODE_ADD)) {
            return gimHeaderMapper.saveGimHeader(gimData);
        } else {
            return gimHeaderMapper.updateGimHeader(gimData);
        }
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<GimDetail> getGimDetail(GimDetail criteria) {
        return gimHeaderMapper.findGimDetail(criteria);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int saveGimDetail(GimDetail gimData) {
        if (StringUtils.equalsIgnoreCase(gimData.getMode(), AppConstants.MODE_ADD)) {
            return gimHeaderMapper.saveGimDetail(gimData);
        } else {
            return gimHeaderMapper.updateGimDetail(gimData);
        }
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int deleteGimDetail(GimDetail[] gimData) {
        int deleteRowCount = 0;
        for (GimDetail gimDetail : gimData) {
            deleteRowCount += gimHeaderMapper.deleteGimDetailByKeys(gimDetail);
        }
        return deleteRowCount;
    }
}
