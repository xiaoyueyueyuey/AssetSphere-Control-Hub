package com.ach.domain.asset.asset.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import com.ach.domain.asset.asset.command.DeleteAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteAssetCommandHandler implements CommandHandler<DeleteAssetCommand> {

    private final AssetRepository repository;


    @Override
    public Boolean handle(EventQueue eventQueue, DeleteAssetCommand command) {
        AssetModel model = repository.findByIdOrError(command.getAssetId());

        return model.handle(eventQueue, command);

    }
}
