package com.ach.domain.asset.procurement;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.procurement.command.ApplyForProcureAssetCommand;
import com.ach.domain.asset.procurement.command.CancelProcureAssetCommand;
import com.ach.domain.asset.procurement.command.ChangeAssetProcurementStatusCommand;
import com.ach.domain.asset.procurement.event.AssetApplyForProcureEvent;
import com.ach.domain.asset.procurement.event.AssetProcurementCancelEvent;
import com.ach.domain.asset.procurement.event.AssetProcurementStatusChangeEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AssetProcurementModel {
    private Long procurementId;
    private Boolean procurementStatus;

    public Boolean handle(EventQueue eventQueue, ApplyForProcureAssetCommand command) {
        AssetApplyForProcureEvent event = new AssetApplyForProcureEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, CancelProcureAssetCommand command) {
        checkModelIsExist();
        checkCanBeCancel();
        AssetProcurementCancelEvent event = new AssetProcurementCancelEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, ChangeAssetProcurementStatusCommand command) {
        checkModelIsExist();
        checkCanChangeProcurementStatus();
        AssetProcurementStatusChangeEvent event = new AssetProcurementStatusChangeEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }

    private void checkCanChangeProcurementStatus() {
        //如果已经完成了，就不能再修改了
        if (procurementStatus != null && procurementStatus) {
            throw new ApiException(ErrorCode.Business.PROCUREMENT_HAS_BEEN_FINISHED);
        }
    }

    public void checkModelIsExist() {
        if (procurementId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_LENDING_NOT_EXIST);
        }
    }

    public void checkCanBeCancel() {
        if (procurementStatus != null && procurementStatus) {
            throw new ApiException(ErrorCode.Business.PROCUREMENT_HAS_BEEN_FINISHED);
        }
    }


}
