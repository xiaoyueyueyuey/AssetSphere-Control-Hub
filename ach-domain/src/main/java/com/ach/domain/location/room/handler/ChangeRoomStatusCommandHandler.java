package com.ach.domain.location.room.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import com.ach.domain.location.room.command.ChangeRoomStatusCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ChangeRoomStatusCommandHandler implements CommandHandler<ChangeRoomStatusCommand> {
    @Resource
    private RoomRepository roomRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, ChangeRoomStatusCommand command) {
        RoomModel model = roomRepository.findByIdOrError(Long.valueOf(command.getRoomId()));

        return model.handle(eventQueue, command);
    }
}
