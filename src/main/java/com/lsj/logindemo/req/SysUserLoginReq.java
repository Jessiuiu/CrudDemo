package com.lsj.logindemo.req;

import lombok.Data;

@Data
public class SysUserLoginReq {
    private String LoginName;
    private String password;
}
