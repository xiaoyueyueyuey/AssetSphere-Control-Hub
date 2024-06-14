package com.ach.domain.asset.lending.handler;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import com.ach.domain.asset.lending.AssetLendingModel;
import com.ach.domain.asset.lending.AssetLendingRepository;
import com.ach.domain.asset.lending.command.LentOutAssetCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LentOutAssetCommandHandler implements CommandHandler<LentOutAssetCommand> {
    private final AssetLendingRepository repository;
    private final AssetRepository assetRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, LentOutAssetCommand command) {
        AssetLendingModel assetLendingModel = repository.findByIdOrError(command.getLendingId());
        AssetModel assetModel = assetRepository.findByIdOrError(assetLendingModel.getAssetId());
        // 申请借出资产，看看资产有没有 同时要状态为0才能借
        assetLendingModel.setAssetStatusIsCanBeLend(assetModel.getAssetId() != null && assetModel.getStatus() == 0);
        return assetLendingModel.handle(eventQueue, command);
    }
}
