package com.dxc.application.feature.usermaster.data.database;


import com.dxc.application.feature.usermaster.data.database.model.User;
import com.dxc.application.feature.usermaster.data.database.model.UserSearchCriteria;
import com.dxc.application.feature.usermaster.dto.UserResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMasterMapper {

    public UserResultDTO findUserPrimaryKey(@Param("citizenId") String citizenId);

    public List<UserResultDTO> findUser(UserSearchCriteria user);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(User user);

}
