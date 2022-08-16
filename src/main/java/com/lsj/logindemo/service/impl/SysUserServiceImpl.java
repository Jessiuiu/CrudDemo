package com.lsj.logindemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsj.logindemo.entity.SysUserEntity;
import com.lsj.logindemo.mapper.SysUserMapper;
import com.lsj.logindemo.req.SysUserLoginReq;
import com.lsj.logindemo.req.SysUserSaveReq;
import com.lsj.logindemo.service.SysUserService;
import com.lsj.logindemo.utils.CopyUtil;
import com.lsj.logindemo.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void register(SysUserSaveReq req) {
        SysUserEntity user= CopyUtil.copy(req,SysUserEntity.class);
        if (ObjectUtils.isEmpty(req.getId())){
            SysUserEntity userDb=selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDb)){
                user.setId(snowFlake.nextId());
                sysUserMapper.insert(user);
            }
        }
    }

    @Override
    public SysUserLoginReq login(SysUserLoginReq req) {
       SysUserEntity userDb=selectByLoginName(req.getLoginName());
       if (ObjectUtils.isEmpty(userDb)){
           //用户不存在
           return null;
       }else {
           //登录成功
           SysUserLoginReq userLoginReq=CopyUtil.copy(userDb,SysUserLoginReq.class);
           return userLoginReq;
       }
    }

    //查询loginName是否被注册
    public SysUserEntity selectByLoginName(String loginName){
        QueryWrapper<SysUserEntity> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(SysUserEntity::getLoginName,loginName);
        List<SysUserEntity> userEntityList=sysUserMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(userEntityList)){
            return null;
        }else {
            return userEntityList.get(0);
        }
    }
}
