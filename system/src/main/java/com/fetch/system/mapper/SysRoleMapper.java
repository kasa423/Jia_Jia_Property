package com.fetch.system.mapper;

import com.fetch.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/11 13:56
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 同步角色与菜单的关系
     * @param roleId    角色id
     * @param menuIds   菜单id
     * @return Boolean 是否成功
     */
    Boolean associateRolesWithMenus(Integer roleId,Integer[] menuIds);

    /**
     * 同步角色与用户的关系
     * @param userId    用户id
     * @param roleIds   角色id
     * @return  Boolean 是否成功
     */
    Boolean associateUserWithRoles(Integer userId,Integer[] roleIds);

    /**
     * 查询用户的角色id列表
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Integer> queryRoleIdList(Integer userId);

    List<SysRole> queryRoleList(Integer userId);
}
