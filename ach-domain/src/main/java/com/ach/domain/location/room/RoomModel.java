package com.ach.domain.location.room;

import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.location.room.command.AddRoomCommand;
import com.ach.domain.location.room.command.ChangeRoomStatusCommand;
import com.ach.domain.location.room.command.DeleteRoomCommand;
import com.ach.domain.location.room.command.UpdateRoomCommand;
import com.ach.domain.location.room.event.RoomAddEvent;
import com.ach.domain.location.room.event.RoomDeleteEvent;
import com.ach.domain.location.room.event.RoomStatusChangeEvent;
import com.ach.domain.location.room.event.RoomUpdateEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoomModel {
    private Integer roomId;
    private String roomName;
    private Integer locationId;
    private Boolean status;

    private Boolean hasAsset;
    private Boolean hasLocation;
    private Boolean nameIsUnique;
    private Boolean isChangeLocation;


    public Boolean handle(EventQueue eventQueue, AddRoomCommand command) {
        try {
            checkNameIsUnique();
            checkHasLocation();
        } catch (ApiException e) {
            return false;
        }
        RoomAddEvent event = new RoomAddEvent();
        BeanUtils.copyProperties(command, event);
        eventQueue.enqueue(event);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, UpdateRoomCommand command) {
        try {
            checkRoomExist();
            checkNameIsUnique();
            checkHasLocation();
        } catch (ApiException e) {
            return false;
        }
        RoomUpdateEvent roomUpdateEvent = new RoomUpdateEvent();
        BeanUtils.copyProperties(command, roomUpdateEvent);
        eventQueue.enqueue(roomUpdateEvent);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, ChangeRoomStatusCommand command) {
        try {
            checkRoomExist();
        } catch (ApiException e) {
            return false;
        }
        RoomStatusChangeEvent event = new RoomStatusChangeEvent();
        BeanUtils.copyProperties(command, event);
        event.setStatus(!this.getStatus());
        eventQueue.enqueue(event);
        return true;
    }
    public Boolean handle(EventQueue eventQueue, DeleteRoomCommand command) {
        try {
            checkRoomExist();
            checkHasAsset();
        } catch (ApiException e) {
            return false;
        }
        RoomDeleteEvent roomDeleteEvent = new RoomDeleteEvent();
        BeanUtils.copyProperties(command, roomDeleteEvent);
        eventQueue.enqueue(roomDeleteEvent);
        return true;
    }

    private void checkHasAsset() {
        if (hasAsset) {
            throw new ApiException(ErrorCode.Business.ROOM_HAS_ASSET_NOT_ALLOW_DELETE);
        }
    }
    private void checkHasLocation() {
        if (!hasLocation) {
            throw new ApiException(ErrorCode.Business.LOCATION_IS_NOT_EXIST);
        }
    }
    private void checkNameIsUnique() {
        if (!nameIsUnique) {
            throw new ApiException(ErrorCode.Business.ROOM_NAME_IS_NOT_UNIQUE);
        }
    }
    private void checkRoomExist() {
        if (roomId == null) {
            throw new ApiException(ErrorCode.Business.ROOM_IS_NOT_EXIST);
        }
    }


}
