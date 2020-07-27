package org.example.service.impl;

import org.example.dao.MenuMapper;
import org.example.entity.Menu;
import org.example.entity.MenuExample;
import org.example.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllTreeNode() {
        List<Menu> menuList=menuMapper.selectByExample(new MenuExample());
        return menuList;

    }

    @Override
    public void updataName(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeNode(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addNode(Menu menu) {
        menuMapper.insertSelective(menu);
    }
}
