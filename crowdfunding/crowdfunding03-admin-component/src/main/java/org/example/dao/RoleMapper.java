package org.example.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Role;
import org.example.entity.RoleExample;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> selectRoleByKeyword(String keyword);

    List<Role> selectRoleByAdminId(Integer id);

    List<Role> selectNotRoleByAdminId(Integer id);
    void removeRoleById(Integer adminId);
    void addRoleforAdmin(@Param(value = "roleIdList") List<Integer> roleIdList,@Param(value = "adminId") Integer adminId);
}