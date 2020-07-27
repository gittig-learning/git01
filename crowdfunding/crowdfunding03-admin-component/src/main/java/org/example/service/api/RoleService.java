package org.example.service.api;

import com.github.pagehelper.PageInfo;
import org.example.entity.Role;

import java.util.List;

public interface RoleService {
    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void addRole(String roleName);

    void removeRole(List<Integer> rolesIdList);

    void updataRole(Role role);
    List<Role> getAdminHasRole(Integer id);

    List<Role> getAdminHasNotRole(Integer id);

    void removeOldRole(Integer adminId);

    void addNewRole(Integer adminId, List<Integer> roleIdList);
}
