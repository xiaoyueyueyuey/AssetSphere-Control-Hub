package com.ach.domain.asset.asset.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import com.ach.domain.asset.asset.command.ChangeAssetStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class ChangeAssetStatusCommandHandler implements CommandHandler<ChangeAssetStatusCommand> {
    private final AssetRepository repository;

    @Override
    public Boolean handle(EventQueue eventQueue, ChangeAssetStatusCommand command) {
        AssetModel model = repository.findByIdOrError(command.getAssetId());
        model.setStatus(command.getStatus());
        return model.handle(eventQueue, command);

    }
}
