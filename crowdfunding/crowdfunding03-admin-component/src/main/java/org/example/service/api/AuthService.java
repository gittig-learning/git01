package org.example.service.api;

import org.example.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {
    List<Auth> getAllAuthNode();

    List<Integer> getRoleAuthById(Integer roleId);

    void addRoleAuth(Map<String, List<Integer>> requestMap);
}
