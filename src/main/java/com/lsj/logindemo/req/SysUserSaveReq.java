package com.lsj.logindemo.req;

import lombok.Data;

@Data
public class SysUserSaveReq {
    private int id;
    private String LoginName;
    private String name;
    private String password;
}
