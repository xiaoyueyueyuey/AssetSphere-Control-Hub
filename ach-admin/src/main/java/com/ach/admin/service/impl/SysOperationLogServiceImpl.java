package com.ach.admin.service.impl;


import com.ach.admin.dto.log.OperationLogDTO;
import com.ach.admin.entity.SysOperationLogEntity;
import com.ach.admin.mapper.SysOperationLogMapper;
import com.ach.admin.query.OperationLogQuery;
import com.ach.admin.service.SysOperationLogService;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 *
 * @since 2022-06-08
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLogEntity> implements
        SysOperationLogService {

    @Override
    public PageCustomDTO<OperationLogDTO> getOperationLogList(OperationLogQuery query) {
        Page<SysOperationLogEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<OperationLogDTO> records = page.getRecords().stream().map(OperationLogDTO::new).collect(Collectors.toList());
        return new PageCustomDTO<>(records, page.getTotal());
    }
}
