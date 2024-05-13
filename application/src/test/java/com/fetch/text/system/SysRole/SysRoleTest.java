package com.fetch.text.system.SysRole;

import com.fetch.system.mapper.SysRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/11 14:04
 */
@SpringBootTest(classes = {com.fetch.web.FetchApplication.class})
@RunWith(SpringRunner.class)
public class SysRoleTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void associateRolesWithMenus(){
        Integer roleId = 1;
        Integer[] menusId = {3,4,5};
        Boolean b = sysRoleMapper.associateRolesWithMenus(roleId, menusId);
        System.out.println(b);
    }
}
