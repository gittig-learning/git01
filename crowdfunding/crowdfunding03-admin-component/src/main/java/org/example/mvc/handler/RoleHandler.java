package org.example.mvc.handler;

import com.github.pagehelper.PageInfo;
import org.example.ResultEntity;
import org.example.entity.Role;
import org.example.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "role")
public class RoleHandler {
    @Autowired
    RoleService roleService;
    @ResponseBody
    @RequestMapping(value = "/get/page")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "7") Integer pageSize,
            @RequestParam(value = "keyword",defaultValue = "") String keyword){

        PageInfo<Role> pageInfo=roleService.getPageInfo(pageNum,pageSize,keyword);
        return ResultEntity.successWithData(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/addRole")
    public ResultEntity addRole(String roleName){
        roleService.addRole(roleName);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping(value = "/removeRole")
    public ResultEntity removeRole(@RequestBody List<Integer> rolesIdList){
        System.out.println(rolesIdList.get(0));
        try{

            roleService.removeRole(rolesIdList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ResultEntity.successWithoutData();
    }
    @ResponseBody
    @RequestMapping(value = "/updataRole")
    public ResultEntity updataRole(Role role){
        roleService.updataRole(role);
        return ResultEntity.successWithoutData();
    }
}
