package org.example;

import org.example.dao.AdminMapper;
import org.example.dao.RoleMapper;
import org.example.entity.Admin;
import org.example.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Test
    public void Test_Connection() throws SQLException {
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public void Test_Insert() {
        for (int i=0;i<100;i++){
            Admin admin=new Admin(null,"tom"+i,"110"+i,"汤姆"+i
                    ,"110@qq.com"+i,"2019/3/30 23:41"+i);
            int count=adminMapper.insert(admin);
        }
    }
    @Test
    public void Test_Role_01(){
        for (int i=0;i<300;i++){
            roleMapper.insert(new Role(null,i+"号工程师"));
        }
    }
    @Test
    public void test_md5(){
        String s="3,lkjlk";
        String[] strings=s.split(",");
        System.out.println(strings[1]);
        //System.out.println(Md5Enctyption.md5("110"));
    }
    @Test
    public void Test_Integer(){
        Integer a=2;
        Integer b=2;
        System.out.println(a.equals(b));
        System.out.println(b.equals(a));

    }
}
