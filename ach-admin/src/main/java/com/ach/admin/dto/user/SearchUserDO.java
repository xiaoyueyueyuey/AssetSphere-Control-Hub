package com.ach.admin.dto.user;


import com.ach.admin.entity.SysUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 如果Entity的字段和复杂查询不匹配时   自定义类来接收
 *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchUserDO extends SysUserEntity {
    private String deptName;//部门名称
    private String deptLeader;//部门负责人
}
