package com.ach.domain.asset.procurement.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.procurement.AssetProcurementModel;
import com.ach.domain.asset.procurement.AssetProcurementRepository;
import com.ach.domain.asset.procurement.command.ChangeAssetProcurementStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeAssetProcurementStatusCommandHandler implements CommandHandler<ChangeAssetProcurementStatusCommand> {
    private final AssetProcurementRepository repository;

    @Override
    public Boolean handle(EventQueue eventQueue, ChangeAssetProcurementStatusCommand command) {
        AssetProcurementModel model = repository.findByIdOrError(command.getProcurementId());
        return model.handle(eventQueue, command);
    }
}
