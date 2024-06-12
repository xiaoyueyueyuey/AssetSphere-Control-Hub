package com.ach.admin.service;


import com.ach.admin.dto.config.ConfigDTO;
import com.ach.admin.entity.SysConfigEntity;
import com.ach.admin.query.ConfigQuery;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * 
 * @since 2022-06-09
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 通过key获取配置
     *
     * @param key 配置对应的key
     * @return 配置
     */
    String getConfigValueByKey(String key);

    PageCustomDTO<ConfigDTO> getConfigList(ConfigQuery query);

    ConfigDTO getConfigInfo(Integer configId);
}
