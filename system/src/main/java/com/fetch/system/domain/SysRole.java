package com.fetch.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/11 13:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {

    private Integer id;

    private String name;

    private String roleKey;

    private char status;

    private String description;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    private Integer updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private String remark;
}
