package com.ach.domain.asset.classification.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.ach.domain.asset.classification.command.UpdateACCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateACCommandHandler implements CommandHandler<UpdateACCommand> {


    private final AssetClassificationRepository repository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateACCommand command) {
        AssetClassificationModel model = repository.findByIdOrError(Long.valueOf(command.getAcId()));
        //检查名字是否被修改过
        if (!model.getAcName().equals(command.getAcName())) {
            Boolean nameIsUnique = repository.checkACNameIsUnique(command.getAcName(), command.getAcId());
            model.setNameIsUnique(nameIsUnique);
        } else {
            model.setNameIsUnique(true);
        }

        return model.handle(eventQueue, command);


    }
}
