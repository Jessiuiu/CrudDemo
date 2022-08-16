package com.lsj.logindemo.service;

import com.lsj.logindemo.req.SysUserLoginReq;
import com.lsj.logindemo.req.SysUserSaveReq;

public interface SysUserService {

    void register(SysUserSaveReq req);

    Object login(SysUserLoginReq req);
}

