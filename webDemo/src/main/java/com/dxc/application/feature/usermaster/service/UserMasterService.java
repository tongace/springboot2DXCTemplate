package com.dxc.application.feature.usermaster.service;

import com.dxc.application.feature.common.service.CommonService;
import com.dxc.application.feature.usermaster.data.database.UserMasterMapper;
import com.dxc.application.feature.usermaster.data.database.model.User;
import com.dxc.application.feature.usermaster.data.database.model.UserSearchCriteria;
import com.dxc.application.feature.usermaster.dto.UserResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserMasterService {

    private final UserMasterMapper userMasterMapper;
    private final CommonService commonService;

    @Autowired
    UserMasterService(UserMasterMapper userMasterMapper, CommonService commonService) {
        this.userMasterMapper = userMasterMapper;
        this.commonService = commonService;
    }

    @Transactional(value = "mybastistx", readOnly = true)
    public List<UserResultDTO> searchUser(UserSearchCriteria criteria) {
        return userMasterMapper.findUser(criteria);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int insertUser(User user, MultipartFile userPic) {
        if (userPic != null && !userPic.isEmpty()) {
            Integer fileID = commonService.insertAttachedFile(userPic, user.getCreatedBy());
            user.setPictureId(fileID);
        } else {
            user.setPictureId(0);
        }
        return userMasterMapper.insertUser(user);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int updateUser(User user,MultipartFile userPic) {
        if (userPic != null && !userPic.isEmpty()) {
            commonService.deleteAttachedFileById(user.getPictureId());
            Integer fileID = commonService.insertAttachedFile(userPic, user.getModifiedBy());
            user.setPictureId(fileID);
        }
        return userMasterMapper.updateUser(user);
    }

    @Transactional(value = "mybastistx", rollbackFor = Exception.class)
    public int deleteUser(User[] users) {
        int deleteRowCount = 0;
        for (User user : users) {
            commonService.deleteAttachedFileById(user.getPictureId());
            deleteRowCount += userMasterMapper.deleteUser(user);
        }
        return deleteRowCount;
    }
}
