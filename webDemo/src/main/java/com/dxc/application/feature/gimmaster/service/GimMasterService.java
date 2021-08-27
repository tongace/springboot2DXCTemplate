package com.dxc.application.feature.gimmaster.service;

import com.dxc.application.feature.gimmaster.data.database.GIMMasterMapper;
import com.dxc.application.feature.gimmaster.data.database.model.GimDetail;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeaderSearchCriteria;
import com.dxc.application.feature.gimmaster.dto.DeleteGimDetailDTO;
import com.dxc.application.feature.gimmaster.dto.GimHeaderResultDTO;
import com.dxc.application.feature.gimmaster.dto.SearchGimDetailResultDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GimMasterService {

    private final GIMMasterMapper gimHeaderMapper;
    private final ModelMapper modelMapper;

    @Autowired
    GimMasterService(GIMMasterMapper gimHeaderMapper,ModelMapper modelMapper) {
        this.gimHeaderMapper = gimHeaderMapper;
        this.modelMapper = modelMapper;
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
    public List<SearchGimDetailResultDTO> getGimDetailByGimType(String gimType) {
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
    public int deleteGimDetail(DeleteGimDetailDTO[] deleteGimDetail) {
        int deleteRowCount = 0;
        GimDetail gimDetail = null;
        for (DeleteGimDetailDTO gimDetailDTO : deleteGimDetail) {
            gimDetail = modelMapper.map(gimDetailDTO,GimDetail.class);
            deleteRowCount += gimHeaderMapper.deleteGimDetailByKeys(gimDetail);
        }
        return deleteRowCount;
    }
}
