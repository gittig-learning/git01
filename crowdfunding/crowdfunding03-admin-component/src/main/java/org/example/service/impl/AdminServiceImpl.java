package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.Md5Enctyption;
import org.example.dao.AdminMapper;
import org.example.dao.RoleMapper;
import org.example.entity.Admin;
import org.example.entity.AdminExample;
import org.example.mvc.exception.AddAdminAcctException;
import org.example.mvc.exception.LoginErrorException;
import org.example.mvc.exception.RemoveLoginAdminException;
import org.example.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Admin getAdminByLoginAcc(String loginAccount, String loginPassword){
        if (loginAccount==null||loginAccount==""){
            throw new LoginErrorException("用户名不能为空");
        }
        // 根据用户名查询admin对象
        AdminExample adminExample=new AdminExample();
        AdminExample.Criteria criteria=adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAccount);

        List<Admin> list=adminMapper.selectByExample(adminExample);
        // 判断对象是否为空
        if(list==null||list.size()==0){
            throw new LoginErrorException("用户名不存在");
        }
        Admin admin=list.get(0);
        if (loginPassword==null||loginPassword==""){
            throw new LoginErrorException("密码不能为空");
        }
        // 比较用户提交密码和该用户数据库密码是否相同
        String databaseCode=admin.getUserPswd();
        String userCode=Md5Enctyption.md5(loginPassword);
        boolean isSame=databaseCode.equals(userCode);
        // 密码一致返回admin
        if(!isSame){
            throw new LoginErrorException("密码格式不正确");
        }
        return admin;
    }
    /**
     * 使用pageHelper实现分页功能
     * @param keyword 搜索关键字
     * @param pageNum 页码
     * @param pageSize 每页数据条数
     * @return List<Admin>封装成pageInfo对象
     * */
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 调用pagehelper的静态方法开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        // 执行查询PageHelper修改sql语句，
        List<Admin> list=adminMapper.selectAdminByKeyword(keyword);
        // 将list集合封装成PageInfo对象
        PageInfo<Admin> pageInfo=new PageInfo<>(list,5);
        return pageInfo;
    }

    @Override
    public void removeAdmin(Admin admin, HttpServletRequest request) {
        HttpSession session=request.getSession();
        Admin loginAdmin= (Admin) session.getAttribute("loginAdmin");
        if (loginAdmin.getId()==admin.getId()){
            throw new RemoveLoginAdminException("不能删除正在登录的用户");
        }
        // 根据admin id值删除对应记录
        adminMapper.deleteByPrimaryKey(admin.getId());
    }

    @Override
    public int addAdmin(Admin admin) {
        // 对用户密码进行加密
        String userPawd= Md5Enctyption.md5(admin.getUserPswd());
        admin.setUserPswd(userPawd);
        // 生成创建时间
        Date data=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String creatTime=simpleDateFormat.format(data);
        admin.setCreatTime(creatTime);
        // 执行保存
        int count=0;
        try {
            count=adminMapper.insert(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new AddAdminAcctException("该用户名已被使用");
            }
        }
        return count;
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin admin=adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }


}
