package com.ach.domain.asset.classification;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.classification.command.AddACCommand;
import com.ach.domain.asset.classification.command.ChangeACStatusCommand;
import com.ach.domain.asset.classification.command.DeleteACCommand;
import com.ach.domain.asset.classification.command.UpdateACCommand;
import com.ach.domain.asset.classification.event.ACAddEvent;
import com.ach.domain.asset.classification.event.ACDeleteEvent;
import com.ach.domain.asset.classification.event.ACStatusChangeEvent;
import com.ach.domain.asset.classification.event.ACUpdateEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class AssetClassificationModel {
    private Integer acId;
    private String acName;
    private Boolean status;

    private Boolean nameIsUnique;
    public Boolean handle(EventQueue eventQueue, AddACCommand command) {
        checkNameIsUnique();
        ACAddEvent acAddEvent = new ACAddEvent();
        BeanUtils.copyProperties(command, acAddEvent);
        eventQueue.enqueue(acAddEvent);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, UpdateACCommand command) {

        checkModelIsExist();
        checkNameIsUnique();

        ACUpdateEvent acUpdateEvent = new ACUpdateEvent();
        BeanUtils.copyProperties(command, acUpdateEvent);
        eventQueue.enqueue(acUpdateEvent);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, ChangeACStatusCommand command) {

        checkModelIsExist();

        ACStatusChangeEvent acStatusChangeEvent = new ACStatusChangeEvent();
        BeanUtils.copyProperties(command, acStatusChangeEvent);
        eventQueue.enqueue(acStatusChangeEvent);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, DeleteACCommand command) {
        checkModelIsExist();
        ACDeleteEvent acDeleteEvent = new ACDeleteEvent();
        BeanUtils.copyProperties(command, acDeleteEvent);
        eventQueue.enqueue(acDeleteEvent);
        return true;
    }


    public void checkNameIsUnique() {
        if (!nameIsUnique) {
            throw new ApiException(ErrorCode.Business.ASSET_CLASSIFICATION_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkModelIsExist() {
        if (acId == null) {
            throw new ApiException(ErrorCode.Business.ASSET_CLASSIFICATION_NOT_EXIST);
        }
    }


}
