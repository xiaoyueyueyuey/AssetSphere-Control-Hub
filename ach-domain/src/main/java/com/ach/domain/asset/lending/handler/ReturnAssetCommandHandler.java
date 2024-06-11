package com.ach.domain.asset.lending.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.lending.AssetLendingModel;
import com.ach.domain.asset.lending.AssetLendingRepository;
import com.ach.domain.asset.lending.command.ReturnAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReturnAssetCommandHandler implements CommandHandler<ReturnAssetCommand> {
    private final AssetLendingRepository repository;
    @Override
    public Boolean handle(EventQueue eventQueue, ReturnAssetCommand command) {

        AssetLendingModel model = repository.findByIdOrError(command.getLendingId());
        return model.handle(eventQueue, command);
    }
}
