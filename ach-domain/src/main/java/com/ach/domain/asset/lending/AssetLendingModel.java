package com.ach.domain.asset.lending;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.lending.command.ApplyForLendingAssetCommand;
import com.ach.domain.asset.lending.command.CancelAssetLendingCommand;
import com.ach.domain.asset.lending.command.LentOutAssetCommand;
import com.ach.domain.asset.lending.command.ReturnAssetCommand;
import com.ach.domain.asset.lending.event.AssetApplyForLendingEvent;
import com.ach.domain.asset.lending.event.AssetLendingCancelEvent;
import com.ach.domain.asset.lending.event.AssetLentOutEvent;
import com.ach.domain.asset.lending.event.AssetReturnEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AssetLendingModel {
    private Long lendingId;
    private Long assetId;
    private Boolean returnStatus;
    private Boolean canCancel;//是否可以取消申请
    private Boolean assetStatusIsCanBeLend;//资产状态是否可以借出
    public Boolean handle(EventQueue eventQueue, ApplyForLendingAssetCommand command) {
        checkCanBeLend();
        AssetApplyForLendingEvent event = new AssetApplyForLendingEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, CancelAssetLendingCommand command) {
        checkModelIsExist();
        checkCanBeCancel();
        AssetLendingCancelEvent event = new AssetLendingCancelEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, ReturnAssetCommand command) {
        checkModelIsExist();
        AssetReturnEvent event = new AssetReturnEvent();
        BeanUtils.copyProperties(command, event);
        event.setAssetId(assetId);
        eventQueue.enqueue(event);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, LentOutAssetCommand command) {
        checkModelIsExist();
        checkCanBeLend();
        AssetLentOutEvent assetLentOutEvent = new AssetLentOutEvent();
        BeanUtils.copyProperties(command, assetLentOutEvent);
        assetLentOutEvent.setAssetId(assetId);
        eventQueue.enqueue(assetLentOutEvent);
        return true;
    }

    public void checkModelIsExist() {
        if (lendingId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_LENDING_NOT_EXIST);
        }
    }

    public void checkCanBeCancel() {
        if (!canCancel) {
            throw new ApiException(ErrorCode.Business.ASSET_NOT_RETURNED);
        }
    }

    public void checkCanBeLend() {
        if (!assetStatusIsCanBeLend) {
            throw new ApiException(ErrorCode.Business.ASSET_CAN_NOT_BE_LEND);
        }
    }


}
