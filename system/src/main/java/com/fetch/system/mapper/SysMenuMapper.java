package com.fetch.system.mapper;

import com.fetch.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    /**
     * 根据角色id 查询菜单
     * @return 菜单
     */
    List<SysMenu> selectMenuByRoleId(Integer roleId);

    /**
     *  新增菜单
     * @param sysMenu   菜单
     * @return 成功条数
     */
    Integer saveNewMenu(SysMenu sysMenu);

///    void update(SysMenu sysMenu);
//
//    void delete(Integer id);
}
