package com.lsj.logindemo.controller;

import com.lsj.logindemo.req.SysUserLoginReq;
import com.lsj.logindemo.req.SysUserSaveReq;
import com.lsj.logindemo.resp.CommonResp;
import com.lsj.logindemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("register")
    public CommonResp register(@RequestBody SysUserSaveReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp=new CommonResp<>();
        sysUserService.register(req);
        return resp;
    }
    @PostMapping("login")
    public CommonResp login(@RequestBody SysUserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp=new CommonResp<>();
        Object loginResp= sysUserService.login(req);
        resp.setContent(loginResp);
        return resp;
    }
}
