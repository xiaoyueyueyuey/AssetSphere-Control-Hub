package com.ach.domain.location.room.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import com.ach.domain.location.room.RoomService;
import com.ach.domain.location.room.command.UpdateRoomCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoomCommandHandler implements CommandHandler<UpdateRoomCommand> {
    @Resource
    private RoomService roomService;
    @Resource
    private RoomRepository roomRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateRoomCommand command) {
        RoomModel model = roomRepository.findByIdOrError(Long.valueOf(command.getRoomId()));
        //检查名字是否被修改过
        if (!model.getRoomName().equals(command.getRoomName())) {
            Boolean roomNameIsUnique = roomRepository.checkRoomNameIsUnique(command.getRoomName(), command.getRoomId());
            model.setNameIsUnique(roomNameIsUnique);
        } else {
            model.setNameIsUnique(true);
        }
        //检查教学楼是否修改过
        if (!model.getLocationId().equals(command.getLocationId())) {
            Boolean locationIdIsExist = roomService.checkLocationIdIsExist(command.getLocationId());
            model.setHasLocation(locationIdIsExist);
            model.setIsChangeLocation(true);
        } else {
            model.setHasLocation(true);
        }
        return model.handle(eventQueue, command);


    }
}
