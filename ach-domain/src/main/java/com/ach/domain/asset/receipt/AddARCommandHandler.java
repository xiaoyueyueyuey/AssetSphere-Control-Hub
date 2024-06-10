package com.ach.domain.asset.receipt;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddARCommandHandler implements CommandHandler<AddARCommand> {
    private final RoomRepository roomRepository;
    private final AssetClassificationRepository acRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AddARCommand command) {
        ARModel arModel = new ARModel();
        RoomModel roomModel = roomRepository.findByIdOrError(Long.valueOf(command.getRoomId()));
        AssetClassificationModel acModel = acRepository.findByIdOrError(Long.valueOf(command.getAcId()));
        if (acModel.getAcId() != null) {
            arModel.setAcIsExist(true);
        }
        if (roomModel.getRoomId() != null) {
            arModel.setRoomIsExist(true);
        }
        return arModel.handle(eventQueue, command);
    }
}
