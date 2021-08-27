package com.dxc.application.feature.gimmaster.data.database;

import com.dxc.application.feature.gimmaster.data.database.model.GimDetail;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeaderSearchCriteria;
import com.dxc.application.feature.gimmaster.dto.GimHeaderResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GIMMasterMapper {
    public List<GimHeaderResultDTO> findGimHeader(GimHeaderSearchCriteria gimHeader);

    public int insertGimHeader(GimHeader criteria);

    public int updateGimHeader(GimHeader criteria);

    public List<GimDetail> findGimDetailByGimType(@Param("gimType") String gimType);

    public int saveGimDetail(GimDetail criteria);

    public int updateGimDetail(GimDetail criteria);

    public int deleteGimDetailByKeys(GimDetail criteria);
}
