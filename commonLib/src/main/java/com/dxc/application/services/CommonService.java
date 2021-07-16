package com.dxc.application.services;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.model.Combo;
import com.dxc.application.mybatis.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CommonService {
	
	@Autowired
	CommonMapper commonMapper;

	@Transactional(value="mybastistx",readOnly = true)
	public Date getDBServerTime(){
		return commonMapper.getDBDateTime();
	}

	@Transactional(value="mybastistx",readOnly = true)
	public List<Combo> getGimTypeCombo(){
		return commonMapper.getGimTypeCombo();
	}

	@Transactional(value="mybastistx",readOnly = true)
	public List<Combo> getActiveFlagCombo(){
		return commonMapper.getActiveFlagCombo(AppConstants.ACTIVE_FLAG_ACTIVE);
	}

}
