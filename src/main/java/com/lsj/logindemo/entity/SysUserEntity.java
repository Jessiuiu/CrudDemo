package com.lsj.logindemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//设置实体类所对应的表名
@TableName("sys_user")
public class SysUserEntity {
    private Long id;
    private String LoginName;
    private String name;
    private String password;
}

