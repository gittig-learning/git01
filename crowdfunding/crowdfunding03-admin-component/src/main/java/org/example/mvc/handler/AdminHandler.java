package org.example.mvc.handler;

import com.github.pagehelper.PageInfo;
import org.example.entity.Admin;
import org.example.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "admin")
public class AdminHandler {
    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/login")
    public String doLogin(String loginAcct,String loginPassword,HttpSession session){
        Admin admin=adminService.getAdminByLoginAcc(loginAcct,loginPassword);
        session.setAttribute("loginAdmin",admin);

        return "redirect:/admin/to/main/page";
    }
    @RequestMapping(value = "/logout")
    public String doLogout(HttpSession session){
        // 强制Session失效
        session.invalidate();
        return "redirect:/admin/to/login/page";
    }
    @RequestMapping(value = "/page")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "8") Integer pageSize,
                              ModelMap modelMap,HttpServletRequest request){
        System.out.println("removeLoginAdminException异常是否放到了请求作用域中"+request.getAttribute("exception"));
        // 解决表单post请求，但url带参，参数名与表单属性名相同的bug，导致keyword值变形

        String[] strings=keyword.split(",");
        if (strings.length>1){
            keyword=strings[1];
        }
        // 调用service方法，获得pageInfo对象
        System.out.println("字符串："+keyword+"  长度："+keyword.length()
                +"  获得keyword参数："+request.getParameter("keyword"));
        PageInfo<Admin> pageInfo=adminService.getPageInfo(keyword,pageNum,pageSize);
        modelMap.addAttribute("pageInfo",pageInfo);
        return "admin-page";
    }
    @RequestMapping(value = "/removeAdmin")
    public String removeAdmin(Admin admin,String pageNum,String keyword,HttpServletRequest request){

        adminService.removeAdmin(admin,request);
        return "redirect:/admin/page?pageNum="+pageNum+"&keyword="+keyword;
    }
    @RequestMapping(value = "/addAdmin")
    public String addAdmin(Admin admin){
        adminService.addAdmin(admin);
        return "redirect:/admin/page？pageNum="+Integer.MAX_VALUE;
    }
    @RequestMapping(value = "update/review")
    public String getUpdateAdmin(Integer id,HttpServletRequest request,ModelMap modelMap){
        Admin admin=adminService.getAdminById(id);
        //request.setAttribute("updateAdmin",admin);
        modelMap.addAttribute("updateAdmin",admin);
        return "admin-update";
    }
    @RequestMapping(value = "/update")
    public String updateAdmin(Admin admin,String pageNum,String keyword){
        adminService.updateAdmin(admin);
        System.out.println("pageNum:"+pageNum+"keyword:"+keyword);
        return "redirect:/admin/page?pageNum="+pageNum+"&keyword="+keyword;
    }
}
