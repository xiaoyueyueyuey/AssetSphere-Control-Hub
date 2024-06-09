package com.ach.domain.location.room.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import com.ach.domain.location.room.RoomService;
import com.ach.domain.location.room.command.DeleteRoomCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoomCommandHandler implements CommandHandler<DeleteRoomCommand> {
    @Resource
    private RoomRepository roomRepository;
    @Resource
    private RoomService roomService;

    @Override
    public Boolean handle(EventQueue eventQueue, DeleteRoomCommand command) {
        RoomModel model = roomRepository.findByIdOrError(Long.valueOf(command.getRoomId()));
        Boolean roomHasAsset = roomService.checkHasAsset(model.getRoomId());
        model.setHasAsset(roomHasAsset);
        return model.handle(eventQueue, command);

    }
}
