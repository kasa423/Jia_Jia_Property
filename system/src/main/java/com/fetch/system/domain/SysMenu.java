package com.fetch.system.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu {

    private Integer id;

    private String menuName;

    private String path;

    private String component;

    private String permissions;

    private Integer parentId;

    private String icon;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date updateTime;

    private Integer updateBy;

    private String remark;

}
