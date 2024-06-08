package com.ach.admin.dto.config;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.ach.admin.entity.SysConfigEntity;
import com.ach.common.enums.BasicEnumUtil;
import com.ach.common.enums.common.YesOrNoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author valarchie
 */
@Data
@Schema(name = "ConfigDTO", description = "配置信息")
public class ConfigDTO {

    private String configId;
    private String configName;
    private String configKey;
    private String configValue;
    private List<String> configOptions;
    private Integer isAllowChange;
    private String isAllowChangeStr;
    private String remark;
    private Date createTime;
    public ConfigDTO(SysConfigEntity entity) {
        if (entity != null) {
            configId = entity.getConfigId() + "";
            configName = entity.getConfigName();
            configKey = entity.getConfigKey();
            configValue = entity.getConfigValue();
            configOptions =
                    JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                            String.class) : ListUtil.empty();
            isAllowChange = Convert.toInt(entity.getIsAllowChange());
            isAllowChangeStr = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, entity.getIsAllowChange());
            remark = entity.getRemark();
            createTime = entity.getCreateTime();
        }
    }

}