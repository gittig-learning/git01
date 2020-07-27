package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Auth;
import org.example.entity.AuthExample;

import java.util.List;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);
    List<Integer> selectRoleAuthByID(Integer roleId);

    void removeAuthByRoleId(Integer roleId);

    void insertAythForRole(@Param(value = "roleId") Integer roleId, @Param(value = "authIdList") List<Integer> authIdList);
}