package org.example.mvc.handler;

import org.example.ResultEntity;
import org.example.entity.Auth;
import org.example.entity.Role;
import org.example.service.api.AuthService;
import org.example.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "assign")
public class AssignHandler {
    @Autowired
    RoleService roleService;
    @Autowired
    AuthService authService;
    @RequestMapping(value = "get/role")
    public ModelAndView showRoleAndAdminRelation(Integer id){
        Role role=new Role();

        List<Role> roleList1=roleService.getAdminHasRole(id);
        List<Role> roleList2=roleService.getAdminHasNotRole(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("adminHasRole",roleList1);
        modelAndView.addObject("adminHasNotRole",roleList2);
        modelAndView.setViewName("admin-role");
        return modelAndView;
    }
    @RequestMapping(value = "set/role")
    public String setAdminRole(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "adminId") Integer adminId,
            @RequestParam(value = "roleId",required = false) List<Integer> roleIdList){
        System.out.println(roleIdList.size());
        roleService.removeOldRole(adminId);
        if(roleIdList!=null&&roleIdList.size()!=0){
            roleService.addNewRole(adminId,roleIdList);
        }
        return "redirect:/admin/page?pageNum="+pageNum+"&keyword="+keyword;
    }
    @RequestMapping(value = "get/treeData")
    @ResponseBody
    public ResultEntity<List<Auth>> getAllAuthNode(){
        List<Auth> authList=authService.getAllAuthNode();
        return ResultEntity.successWithData(authList);
    }

    @RequestMapping(value = "get/roleAuth")
    @ResponseBody
    public ResultEntity<List<Integer>> getAllAuthNode(Integer roleId){
        List<Integer> authIdArr=authService.getRoleAuthById(roleId);
        return ResultEntity.successWithData(authIdArr);
    }
    @RequestMapping(value = "set/roleAuth")
    @ResponseBody
    public ResultEntity setRoleAuth(@RequestBody Map<String,List<Integer>> requestMap){
       authService.addRoleAuth(requestMap);
        return ResultEntity.successWithoutData();
    }
}
