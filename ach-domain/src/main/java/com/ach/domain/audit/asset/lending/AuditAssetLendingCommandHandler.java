package com.ach.domain.audit.asset.lending;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuditAssetLendingCommandHandler implements CommandHandler<AuditAssetLendingCommand> {
    private final AuditAssetLendingRepository auditAssetLendingRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AuditAssetLendingCommand command) {
        AuditAssetLendingModel model = auditAssetLendingRepository.findByIdOrError(command.getLendingId());

        return model.handle(eventQueue, command);

    }

}
