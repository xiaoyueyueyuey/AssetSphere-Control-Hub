package com.ach.domain.asset.procurement.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.procurement.AssetProcurementModel;
import com.ach.domain.asset.procurement.command.ApplyForProcureAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplyForProcureAssetCommandHandler implements CommandHandler<ApplyForProcureAssetCommand> {

    @Override
    public Boolean handle(EventQueue eventQueue, ApplyForProcureAssetCommand command) {
        AssetProcurementModel assetProcurementModel = new AssetProcurementModel();

        return assetProcurementModel.handle(eventQueue, command);

    }
}
