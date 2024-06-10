package com.ach.domain.asset.classification.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.ach.domain.asset.classification.command.DeleteACCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteACCommandHandler implements CommandHandler<DeleteACCommand> {

    private final AssetClassificationRepository repository;


    @Override
    public Boolean handle(EventQueue eventQueue, DeleteACCommand command) {
        AssetClassificationModel model = repository.findByIdOrError(Long.valueOf(command.getAcId()));

        return model.handle(eventQueue, command);

    }
}
