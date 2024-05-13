package com.fetch.common.core.domain;


import lombok.*;

import java.io.Serializable;

/**
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String userType;
    private long createBy;
    private long updateBy;
    private String delFlag;

}
