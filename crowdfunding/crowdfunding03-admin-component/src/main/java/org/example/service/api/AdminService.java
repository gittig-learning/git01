package org.example.service.api;

import com.github.pagehelper.PageInfo;
import org.example.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    Admin getAdminByLoginAcc(String loginAccount, String loginPassword);
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);
    void removeAdmin(Admin admin, HttpServletRequest request);
    int addAdmin(Admin admin);
    void updateAdmin(Admin admin);
    Admin getAdminById(Integer id);


}
