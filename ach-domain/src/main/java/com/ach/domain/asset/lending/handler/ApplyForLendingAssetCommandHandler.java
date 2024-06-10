package com.ach.domain.asset.lending.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import com.ach.domain.asset.lending.AssetLendingModel;
import com.ach.domain.asset.lending.AssetLendingRepository;
import com.ach.domain.asset.lending.command.ApplyForLendingAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplyForLendingAssetCommandHandler implements CommandHandler<ApplyForLendingAssetCommand> {
    private final AssetLendingRepository repository;
    private final AssetRepository assetRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, ApplyForLendingAssetCommand command) {
        AssetLendingModel assetLendingModel = new AssetLendingModel();
        AssetModel assetModel = assetRepository.findByIdOrError(command.getAssetId());
        // 申请借出资产，看看资产有没有 同时要状态为0才能借
        assetLendingModel.setAssetStatusIsCanBeLend(assetModel.getAssetId() != null && assetModel.getStatus() == 0);
        return assetLendingModel.handle(eventQueue, command);

    }
}
