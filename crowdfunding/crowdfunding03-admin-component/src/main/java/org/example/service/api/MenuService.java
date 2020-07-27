package org.example.service.api;

import org.example.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllTreeNode();
    void updataName(Menu menu);
    void removeNode(Integer id);
    void addNode(Menu menu);
}
