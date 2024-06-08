package com.ach.admin.dto.menu;


import cn.hutool.core.util.StrUtil;
import com.ach.admin.entity.SysMenuEntity;
import com.ach.common.enums.BasicEnumUtil;
import com.ach.common.enums.common.MenuTypeEnum;
import com.ach.common.enums.common.StatusEnum;
import com.ach.common.utils.jackson.JacksonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class MenuDTO {

    // 设置成id和parentId 便于前端处理树级结构
    private Long id;
    private Long parentId;
    private String menuName;
    private String routerName;
    private String path;
    private Integer rank;
    private Integer menuType;
    private String menuTypeStr;
    private Boolean isButton;
    private Integer status;
    private String statusStr;
    private Date createTime;
    private String icon;

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.id = entity.getMenuId();
            this.parentId = entity.getParentId();
            this.menuName = entity.getMenuName();
            this.routerName = entity.getRouterName();
            this.path = entity.getPath();
            this.status = entity.getStatus();
            this.isButton = entity.getIsButton();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());

            if (!entity.getIsButton()) {
                this.menuType = entity.getMenuType();
                this.menuTypeStr = BasicEnumUtil.getDescriptionByValue(MenuTypeEnum.class, entity.getMenuType());
            } else {
                this.menuType = 0;
            }

            if (StrUtil.isNotEmpty(entity.getMetaInfo()) && JacksonUtil.isJson(entity.getMetaInfo())) {
                MetaDTO meta = JacksonUtil.from(entity.getMetaInfo(), MetaDTO.class);
                this.rank = meta.getRank();
                this.icon = meta.getIcon();
            }
            this.createTime = entity.getCreateTime();
        }
    }


}
