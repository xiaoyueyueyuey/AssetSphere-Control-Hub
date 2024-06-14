package com.ach.domain.asset.lending.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.asset.lending.AssetLendingModel;
import com.ach.domain.asset.lending.AssetLendingRepository;
import com.ach.domain.asset.lending.command.CancelAssetLendingCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class CancelAssetLendingCommandHandler implements CommandHandler<CancelAssetLendingCommand> {
    private final AssetLendingRepository repository;
    @Override
    public Boolean handle(EventQueue eventQueue, CancelAssetLendingCommand command) {
        AssetLendingModel model = repository.findByIdOrError(command.getLendingId());
        //如果状态有值，说明已经被借出了，不能取消
        if (model.getReturnStatus() != null) {
            model.setCanCancel(false);
            return model.handle(eventQueue, command);
        } else {
            model.setCanCancel(true);
            return model.handle(eventQueue, command);
        }


    }
}
