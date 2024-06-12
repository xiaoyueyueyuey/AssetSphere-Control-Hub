package com.ach.admin.service;


import com.ach.admin.dto.log.OperationLogDTO;
import com.ach.admin.entity.SysOperationLogEntity;
import com.ach.admin.query.OperationLogQuery;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * 
 * @since 2022-06-08
 */
public interface SysOperationLogService extends IService<SysOperationLogEntity> {

    PageCustomDTO<OperationLogDTO> getOperationLogList(OperationLogQuery query);
}
