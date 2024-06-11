package com.ach.domain.audit.asset.lending;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AuditAssetLendingModel {
    private Long lendingId;
    private Byte auditStatus;

    public Boolean handle(EventQueue eventQueue, AuditAssetLendingCommand command) {
        checkModelIsExist();
        checkCanBeAudit();
        AssetLendingAuditEvent event = new AssetLendingAuditEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }

    private void checkModelIsExist() {
        if (lendingId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_LENDING_NOT_EXIST);
        }
    }

    private void checkCanBeAudit() {
        if (auditStatus != 0) {
            throw new ApiException(ErrorCode.Business.ASSET_LENDING_HAS_BEEN_AUDITED);
        }
    }
}
