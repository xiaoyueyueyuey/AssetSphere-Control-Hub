package com.ach.domain.system.dept;


import com.ach.domain.common.Repository;

public interface DeptRepository extends Repository<DeptModel> {
    Boolean checkDeptNameIsUnique(String deptName);

    Boolean checkDeptNameIsUnique(String deptName, Long deptId);

    Boolean checkParentDeptIsExist(Long parentId);
//    Integer getChildDeptCount(Long parentId);//获取子部门数量
//    Integer getUserIsAssignedCount(Long deptId);//获取用户分配数量
}
