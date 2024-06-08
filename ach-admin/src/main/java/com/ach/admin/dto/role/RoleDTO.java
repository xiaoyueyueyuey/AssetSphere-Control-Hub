package com.ach.admin.dto.role;


import com.ach.admin.entity.SysRoleEntity;
import com.ach.common.annotation.excel.ExcelColumn;
import com.ach.common.annotation.excel.ExcelSheet;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author valarchie
 */
@Data
@ExcelSheet(name = "角色列表")
public class RoleDTO {

    @ExcelColumn(name = "角色ID")
    private Long roleId;
    @ExcelColumn(name = "角色名称")
    private String roleName;
    @ExcelColumn(name = "角色标识")
    private String roleKey;
    @ExcelColumn(name = "角色排序")
    private Integer roleSort;
    @ExcelColumn(name = "角色状态")
    private Integer status;
    @ExcelColumn(name = "备注")
    private String remark;
    @ExcelColumn(name = "创建时间")
    private Date createTime;
    @ExcelColumn(name = "数据范围")
    private Integer dataScope;
    private List<Long> selectedMenuList;
    private List<Long> selectedDeptList;

    public RoleDTO(SysRoleEntity entity) {
        if (entity != null) {
            this.roleId = entity.getRoleId();
            this.roleName = entity.getRoleName();
            this.roleKey = entity.getRoleKey();
            this.roleSort = entity.getRoleSort();
            this.createTime = entity.getCreateTime();
            this.status = entity.getStatus();
            this.remark = entity.getRemark();
            this.dataScope = entity.getDataScope();
        }
    }
}