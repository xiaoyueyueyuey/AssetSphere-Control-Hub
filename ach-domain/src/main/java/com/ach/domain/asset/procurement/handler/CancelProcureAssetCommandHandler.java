package com.ach.domain.asset.procurement.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.procurement.AssetProcurementModel;
import com.ach.domain.asset.procurement.AssetProcurementRepository;
import com.ach.domain.asset.procurement.command.CancelProcureAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class CancelProcureAssetCommandHandler implements CommandHandler<CancelProcureAssetCommand> {
    private final AssetProcurementRepository repository;

    @Override
    public Boolean handle(EventQueue eventQueue, CancelProcureAssetCommand command) {
        AssetProcurementModel model = repository.findByIdOrError(command.getProcurementId());

        return model.handle(eventQueue, command);


    }
}
