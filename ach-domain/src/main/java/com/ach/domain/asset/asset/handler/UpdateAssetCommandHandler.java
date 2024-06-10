package com.ach.domain.asset.asset.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import com.ach.domain.asset.asset.command.UpdateAssetCommand;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateAssetCommandHandler implements CommandHandler<UpdateAssetCommand> {


    private final AssetRepository repository;
    private final RoomRepository roomRepository;
    private final AssetClassificationRepository acRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateAssetCommand command) {
        AssetModel model = repository.findByIdOrError(command.getAssetId());
        if (model.getAssetId() == null) {
            return model.handle(eventQueue, command);
        }

        //检查存储库是否修改过
        if (model.getRoomId().compareTo(command.getRoomId()) == 0) {
            model.setRoomIsExist(true);
        } else {
            RoomModel roomModel = roomRepository.findByIdOrError(Long.valueOf(command.getRoomId()));
            model.setRoomIsExist(roomModel.getRoomId() != null);
        }
        //检查分类是否修改过
        if (model.getAcId().compareTo(command.getAcId()) == 0) {
            model.setAcIsExist(true);
        } else {
            //检查分类是否存在
            AssetClassificationModel byIdOrError = acRepository.findByIdOrError(Long.valueOf(command.getAcId()));
            model.setAcIsExist(byIdOrError.getAcId() != null);
        }
        return model.handle(eventQueue, command);


    }
}
