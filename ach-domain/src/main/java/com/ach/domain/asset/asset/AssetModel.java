package com.ach.domain.asset.asset;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.command.ChangeAssetStatusCommand;
import com.ach.domain.asset.asset.command.DeleteAssetCommand;
import com.ach.domain.asset.asset.command.UpdateAssetCommand;
import com.ach.domain.asset.asset.event.AssetDeleteEvent;
import com.ach.domain.asset.asset.event.AssetStatusChangeEvent;
import com.ach.domain.asset.asset.event.AssetUpdateEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AssetModel {
    private Long assetId;
    private Byte status;
    private Integer acId;
    private Integer roomId;

    private Boolean roomIsExist;
    private Boolean acIsExist;

    public Boolean handle(EventQueue eventQueue, UpdateAssetCommand command) {

        checkModelIsExist();
        checkRoomIsExist();
        checkAcIsExist();
        AssetUpdateEvent assetUpdateEvent = new AssetUpdateEvent();
        BeanUtils.copyProperties(command, assetUpdateEvent);
        eventQueue.enqueue(assetUpdateEvent);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, ChangeAssetStatusCommand command) {
        checkModelIsExist();
        checkStatusIsValid();

        AssetStatusChangeEvent assetStatusChangeEvent = new AssetStatusChangeEvent();
        BeanUtils.copyProperties(command, assetStatusChangeEvent);
        eventQueue.enqueue(assetStatusChangeEvent);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, DeleteAssetCommand command) {
        checkModelIsExist();
        AssetDeleteEvent acDeleteEvent = new AssetDeleteEvent();
        BeanUtils.copyProperties(command, acDeleteEvent);
        eventQueue.enqueue(acDeleteEvent);
        return true;
    }


    public void checkModelIsExist() {
        if (assetId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_CLASSIFICATION_NOT_EXIST);
        }
    }

    public void checkRoomIsExist() {
        if (roomIsExist == null || !roomIsExist) {
            throw new ApiException(ErrorCode.Business.ROOM_IS_NOT_EXIST);
        }
    }

    public void checkAcIsExist() {
        if (acIsExist == null || !acIsExist) {
            throw new ApiException(ErrorCode.Business.ASSET_CLASSIFICATION_NOT_EXIST);
        }
    }

    public void checkStatusIsValid() {
        if (status == null || status < 0 || status > 2) {
            throw new ApiException(ErrorCode.Business.COMMOM_STATUS_IS_NOT_VALID);
        }
    }


}
