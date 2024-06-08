package com.ach.admin.query.service.impl;


import com.ach.admin.dto.log.LoginLogDTO;
import com.ach.admin.entity.SysLoginInfoEntity;
import com.ach.admin.mapper.SysLoginInfoMapper;
import com.ach.admin.query.LoginLogQuery;
import com.ach.admin.query.service.SysLoginInfoService;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-07-10
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfoEntity> implements
        SysLoginInfoService {

    @Override
    public PageCustomDTO<LoginLogDTO> getLoginInfoList(LoginLogQuery query) {
        Page<SysLoginInfoEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<LoginLogDTO> records = page.getRecords().stream().map(LoginLogDTO::new).collect(Collectors.toList());
        return new PageCustomDTO<>(records, page.getTotal());
    }
}
