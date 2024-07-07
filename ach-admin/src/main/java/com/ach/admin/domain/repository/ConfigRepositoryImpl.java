package com.ach.admin.domain.repository;

import com.ach.admin.entity.SysConfigEntity;
import com.ach.admin.mapper.SysConfigMapper;
import com.ach.domain.system.config.ConfigModel;
import com.ach.domain.system.config.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor

@Component
public class ConfigRepositoryImpl implements ConfigRepository {
    private final SysConfigMapper sysConfigMapper;

    @Override
    public ConfigModel findByIdOrError(Long id) {

        SysConfigEntity sysConfigEntity = sysConfigMapper.selectById(id);

        ConfigModel configModel = new ConfigModel();
        configModel.setConfigId(Math.toIntExact(id));
        String temp = sysConfigEntity.getConfigOptions().replaceAll("\\[|\\]|\"", "");
        String[] split = temp.split(",");

        Set<String> configOptionSet = new HashSet<>(Arrays.asList(split));
        configModel.setConfigOptionSet(configOptionSet);
        return configModel;


    }

    @Override
    public Long save(ConfigModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }

    @Override
    public Set<String> getConfigOptionSet(Integer configId) {
        return sysConfigMapper.selectConfigOptionSet(configId);
    }
}
