package org.example.service.impl;

import org.example.dao.AuthMapper;


import org.example.entity.Auth;
import org.example.entity.AuthExample;
import org.example.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthMapper authMapper;
    @Override
    public List<Auth> getAllAuthNode() {
        List<Auth> authList=authMapper.selectByExample(new AuthExample());
        return authList;
    }

    @Override
    public List<Integer> getRoleAuthById(Integer roleId) {
        List<Integer> authList=authMapper.selectRoleAuthByID(roleId);
        return authList;
    }

    @Override
    public void addRoleAuth(Map<String, List<Integer>> requestMap) {
        List<Integer> roleIdList=requestMap.get("roleId");
        Integer roleId=roleIdList.get(0);
        authMapper.removeAuthByRoleId(roleId);
        List<Integer> authIdList=requestMap.get("authIdArr");
        if (authIdList==null&&authIdList.size()==0){
            throw new RuntimeException("Role的AuthIdArr数组不能为空");
        }
        authMapper.insertAythForRole(roleId,authIdList);
    }
}
