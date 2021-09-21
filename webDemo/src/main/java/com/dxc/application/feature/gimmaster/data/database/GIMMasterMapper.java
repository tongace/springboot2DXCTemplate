package com.dxc.application.feature.gimmaster.data.database;

import com.dxc.application.feature.gimmaster.data.database.model.GimDetail;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeaderSearchCriteria;
import com.dxc.application.feature.gimmaster.dto.GimHeaderResultDTO;
import com.dxc.application.feature.gimmaster.dto.SearchGimDetailResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GIMMasterMapper {
    List<GimHeaderResultDTO> findGimHeader(GimHeaderSearchCriteria gimHeader);

    int insertGimHeader(GimHeader criteria);

    int updateGimHeader(GimHeader criteria);

    List<SearchGimDetailResultDTO> findGimDetailByGimType(@Param("gimType") String gimType);

    int saveGimDetail(GimDetail criteria);

    int updateGimDetail(GimDetail criteria);

    int deleteGimDetailByKeys(GimDetail criteria);
}
