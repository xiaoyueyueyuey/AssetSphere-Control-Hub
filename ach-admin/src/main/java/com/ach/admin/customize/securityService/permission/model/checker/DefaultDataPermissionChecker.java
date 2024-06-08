package com.ach.admin.customize.securityService.permission.model.checker;


import com.ach.admin.customize.securityService.permission.model.AbstractDataPermissionChecker;
import com.ach.admin.customize.securityService.permission.model.DataCondition;
import com.ach.admin.query.service.SysDeptService;
import com.ach.infrastructure.user.web.SystemLoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限测试接口
 *
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;

    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        return false;
    }

}
