package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.dao.RoleMapper;
import org.example.entity.Role;
import org.example.entity.RoleExample;
import org.example.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 1.调用startPage方法，开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        // 2.执行查询
        List<Role> roleList= roleMapper.selectRoleByKeyword(keyword);
        // 3，将结果封装成PageINfo类型，并返回
        PageInfo<Role> pageInfo=new PageInfo<>(roleList,5);
        return pageInfo;
    }

    @Override
    public void addRole(String roleName) {
        roleMapper.insert(new Role(null,roleName));
    }

    @Override
    public void removeRole(List<Integer> rolesIdList) {

        try{
            RoleExample roleExample=new RoleExample();
            RoleExample.Criteria criteria=roleExample.createCriteria();
            criteria.andIdIn(rolesIdList);
            roleMapper.deleteByExample(roleExample);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("sql语句执行异常");
        }

    }

    @Override
    public void updataRole(Role role) {
        System.out.println(role.toString());
        try{
            roleMapper.updateByPrimaryKey(role);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("sql语句执行异常");
        }

    }
    @Override
    public List<Role> getAdminHasRole(Integer id) {
        List<Role> roleList = roleList = roleMapper.selectRoleByAdminId(id);
        return roleList;
    }
    @Override
    public List<Role> getAdminHasNotRole(Integer id) {
        List<Role> roleList=roleMapper.selectNotRoleByAdminId(id);
        return roleList;
    }

    @Override
    public void removeOldRole(Integer adminId) {
        roleMapper.removeRoleById(adminId);
    }

    @Override
    public void addNewRole(Integer adminId, List<Integer> roleIdList) {
        System.out.println(roleIdList.get(0));
        roleMapper.addRoleforAdmin(roleIdList,adminId);
    }
}
