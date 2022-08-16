package com.lsj.logindemo.resp;

import lombok.Data;

@Data
public class SysUserLoginResp {
    private Long id;
    private String LoginName;
    private String password;
}
