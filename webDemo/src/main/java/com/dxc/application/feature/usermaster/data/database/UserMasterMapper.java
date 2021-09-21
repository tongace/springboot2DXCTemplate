package com.dxc.application.feature.usermaster.data.database;


import com.dxc.application.feature.usermaster.data.database.model.User;
import com.dxc.application.feature.usermaster.data.database.model.UserSearchCriteria;
import com.dxc.application.feature.usermaster.dto.UserResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMasterMapper {

    UserResultDTO findUserPrimaryKey(@Param("citizenId") String citizenId);

    List<UserResultDTO> findUser(UserSearchCriteria user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

}
