package com.dxc.application.feature.common.data.database;

import com.dxc.application.feature.common.data.database.model.AttachedFile;
import com.dxc.application.feature.common.data.database.model.Combo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface CommonMapper {
    Date getDBDateTime();

    List<Combo> getGimTypeCombo();

    List<Combo> getActiveFlagCombo(@Param("activeFlag") String activeFlag);

    AttachedFile getAttachedFileById(@Param("id") BigDecimal id);

    Integer insertAttachedFiled(AttachedFile attachedFile);
}
