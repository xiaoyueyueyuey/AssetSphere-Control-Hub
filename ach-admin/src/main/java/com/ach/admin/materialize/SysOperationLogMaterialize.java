package com.ach.admin.materialize;


import com.ach.admin.entity.SysOperationLogEntity;
import com.ach.admin.mapper.SysOperationLogMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.log.operation.event.OperationLogAddEvent;
import com.ach.domain.system.log.operation.event.OperationLogDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SysOperationLogMaterialize implements DomainEventListener {
    final SysOperationLogMapper sysOperationLogMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof OperationLogDeleteEvent) {
            deleteBatchOperationLog((OperationLogDeleteEvent) event);
        } else if (event instanceof OperationLogAddEvent) {
            addOperationLog((OperationLogAddEvent) event);
        }
    }

    private void addOperationLog(OperationLogAddEvent event) {
        SysOperationLogEntity sysOperationLogEntity = new SysOperationLogEntity();
        BeanUtils.copyProperties(event, sysOperationLogEntity);
        sysOperationLogMapper.insert(sysOperationLogEntity);
    }

    private void deleteBatchOperationLog(OperationLogDeleteEvent event) {
        sysOperationLogMapper.deleteBatchIds(event.getOperationIds());
    }


}
