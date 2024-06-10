package com.ach.domain.asset.receipt;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ARModel {
    private Boolean roomIsExist;
    private Boolean acIsExist;


    public Boolean handle(EventQueue eventQueue, AddARCommand command) {
        checkRoomIsExist();
        checkAcIsExist();
//
        ARAddEvent araddEvent = new ARAddEvent();
        BeanUtils.copyProperties(command, araddEvent);
        eventQueue.enqueue(araddEvent);
        return true;
    }

    private void checkRoomIsExist() {
        if (!roomIsExist) {
            throw new ApiException(ErrorCode.Business.ROOM_IS_NOT_EXIST);
        }
    }

    public void checkAcIsExist() {
        if (!acIsExist) {
            throw new ApiException(ErrorCode.Business.ASSET_CLASSIFICATION_NOT_EXIST);
        }
    }
}
