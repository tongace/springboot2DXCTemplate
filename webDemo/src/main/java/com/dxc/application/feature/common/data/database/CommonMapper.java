package com.dxc.application.feature.common.data.database;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dxc.application.feature.common.data.database.model.AttachedFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dxc.application.feature.common.data.database.model.Combo;

@Mapper
public interface CommonMapper {
	Date getDBDateTime();
	List<Combo> getGimTypeCombo();
	List<Combo> getActiveFlagCombo(@Param("activeFlag") String activeFlag);
    AttachedFile getAttachedFileById(@Param("id") BigDecimal id);
    Integer insertAttachedFiled(AttachedFile attachedFile);
}
