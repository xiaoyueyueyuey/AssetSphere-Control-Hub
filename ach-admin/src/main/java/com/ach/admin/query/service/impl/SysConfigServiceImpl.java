package com.ach.admin.query.service.impl;


import cn.hutool.core.util.StrUtil;
import com.ach.admin.dto.config.ConfigDTO;
import com.ach.admin.entity.SysConfigEntity;
import com.ach.admin.mapper.SysConfigMapper;
import com.ach.admin.query.ConfigQuery;
import com.ach.admin.query.service.SysConfigService;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-09
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements
        SysConfigService {

    @Override
    public String getConfigValueByKey(String key) {
        if (StrUtil.isBlank(key)) {
            return StrUtil.EMPTY;
        }
        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        SysConfigEntity one = this.getOne(queryWrapper);
        if (one == null || one.getConfigValue() == null) {
            return StrUtil.EMPTY;
        }
        return one.getConfigValue();
    }

    @Override
    public PageCustomDTO<ConfigDTO> getConfigList(ConfigQuery query) {
        Page<SysConfigEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<ConfigDTO> records = page.getRecords().stream().map(ConfigDTO::new).collect(Collectors.toList());
        return new PageCustomDTO<>(records, page.getTotal());
    }

    @Override
    public ConfigDTO getConfigInfo(Integer configId) {
        SysConfigEntity byId = this.getById(configId);
        return new ConfigDTO(byId);
    }


}
