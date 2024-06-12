package com.ach.admin.customize.securityService.permission.model.checker;


import cn.hutool.core.collection.CollUtil;
import com.ach.admin.customize.securityService.permission.model.AbstractDataPermissionChecker;
import com.ach.admin.customize.securityService.permission.model.DataCondition;
import com.ach.admin.service.SysDeptService;
import com.ach.infrastructure.user.web.SystemLoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 数据权限测试接口
 *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;


    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getRoleInfo() == null) {
            return false;
        }

        Set<Long> deptIdSet = loginUser.getRoleInfo().getDeptIdSet();
        Long targetDeptId = condition.getTargetDeptId();

        return condition.getTargetDeptId() != null && CollUtil.safeContains(deptIdSet, targetDeptId);
    }
}
