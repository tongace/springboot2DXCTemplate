package com.dxc.application.feature.gimmaster.service;

import com.dxc.application.feature.gimmaster.data.database.GIMMasterMapper;
import com.dxc.application.feature.gimmaster.data.database.model.GimDetail;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeaderSearchCriteria;
import com.dxc.application.feature.gimmaster.dto.GimHeaderResultDTO;
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
    public List<GimHeaderResultDTO> searchGimHeader(GimHeaderSearchCriteria criteria) {
        return gimHeaderMapper.findGimHeader(criteria);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int insertGimHeader(GimHeader gimData) {
        return gimHeaderMapper.insertGimHeader(gimData);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int updateGimHeader(GimHeader gimData) {
        return gimHeaderMapper.updateGimHeader(gimData);
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<GimDetail> getGimDetailByGimType(String gimType) {
        return gimHeaderMapper.findGimDetailByGimType(gimType);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int insertGimDetail(GimDetail gimData) {
        return gimHeaderMapper.saveGimDetail(gimData);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int updateGimDetail(GimDetail gimData) {
        return gimHeaderMapper.updateGimDetail(gimData);
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
