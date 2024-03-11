package com.fetch.common.core.mapper;

import com.fetch.common.core.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/11 9:29
 */
@Mapper
public interface UserMapper {

    SysUser selectUserByUsername(SysUser user);

}
