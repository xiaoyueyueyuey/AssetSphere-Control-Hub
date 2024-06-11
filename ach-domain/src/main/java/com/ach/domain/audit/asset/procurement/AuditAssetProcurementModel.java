package com.ach.domain.audit.asset.procurement;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AuditAssetProcurementModel {
    private Long procurementId;
    private Byte auditStatus;

    public Boolean handle(EventQueue eventQueue, AuditAssetProcurementCommand command) {
        checkModelIsExist();
        checkCanBeAudit(command.getAuditStatus());
        AssetProcurementAuditEvent event = new AssetProcurementAuditEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }

    private void checkModelIsExist() {
        if (procurementId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_PROCUREMENT_NOT_EXIST);
        }
    }

    private void checkCanBeAudit(Byte status) {
        if (auditStatus != 0) {
            throw new ApiException(ErrorCode.Business.ASSET_PROCUREMENT_HAS_BEEN_AUDITED);
        }
        if (status != 1 && status != 2) {
            throw new ApiException(ErrorCode.Business.COMMOM_STATUS_IS_NOT_VALID);
        }
    }
}
