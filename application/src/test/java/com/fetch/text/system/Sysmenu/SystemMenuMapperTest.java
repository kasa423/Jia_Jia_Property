package com.fetch.text.system.Sysmenu;

import com.fetch.system.domain.SysMenu;
import com.fetch.system.mapper.SysMenuMapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/7 17:52
 */
@SpringBootTest(classes = {com.fetch.web.FetchApplication.class})
@MapperScan("com.fetch.system.mapper")
@RunWith(SpringRunner.class)
public class SystemMenuMapperTest {
    @Autowired
    private SysMenuMapper SysMenuMapper;

    @Test
    public void testSelectMenuByRoleId(){
        List<SysMenu> sysMenus = SysMenuMapper.selectMenuByRoleId(1);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu);
        }
    }

    @Test
    public void testSaveMenu(){
        SysMenu sysMenu = new SysMenu(null,
                "菜单名1", "/menu1",
                "菜单1", "system:menu:all",
                0, "", null, null,
                null, 2, "测试数据");
        SysMenuMapper.saveNewMenu(sysMenu);
        Integer id = sysMenu.getId();
        System.out.println(id);
    }
}
