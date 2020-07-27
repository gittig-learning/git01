package org.example.mvc.handler;

import org.example.ResultEntity;
import org.example.entity.Menu;
import org.example.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "menu")
public class MenuHandler {
    @Autowired
    private MenuService menuService;
    @RequestMapping(value = "/get/tree")
    @ResponseBody
    public ResultEntity<Menu> getWholeTreeNew(){
        //1.查询全部的Menu对象
        List<Menu> menuList=menuService.getAllTreeNode();
        //2.声明一个变量用来存储找到的根节点
        Menu root=null;
        //3.将子节点放入到对应根节点的children属性中
        Map<Integer,Menu> menuMap=new HashMap<>();
        for(Menu menu:menuList){
            Integer id=menu.getId();
            menuMap.put(id,menu);
        }
        for (Menu menu:menuList){
            Integer pid=menu.getPid();
            // 获得根节点
            if (pid==null){
                root=menu;
                continue;
            }
            // 获得父节点
            Menu fatherMenu=menuMap.get(pid);
            fatherMenu.getChildren().add(menu);
        }
        // 循环嵌套，循环次数为两次循环次数乘积
//        for(Menu menu:menuList){
//            Integer pid=menu.getPid();
//            if (pid==null){
//                root=menu;
//                continue;
//            }
//            for (Menu fatherMenu:menuList){
//                Integer id=fatherMenu.getId();
//                if(Objects.equals(id,pid)){
//                    fatherMenu.getChildren().add(menu);
//                    break;
//                }
//            }
//        }
        //4.返回根节点
        return ResultEntity.successWithData(root);
    }

    @RequestMapping(value = "edit/nodeName")
    @ResponseBody
    public ResultEntity updateNodeName(Menu menu){
        menuService.updataName(menu);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping(value = "remove/nodeName")
    @ResponseBody
    public ResultEntity removeNode(Integer id){
        menuService.removeNode(id);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping(value = "add/node")
    @ResponseBody
    public ResultEntity addNode(Menu menu){
        menuService.addNode(menu);
        return ResultEntity.successWithoutData();
    }
}
