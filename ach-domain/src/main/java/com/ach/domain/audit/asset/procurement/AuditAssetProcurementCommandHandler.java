package com.ach.domain.audit.asset.procurement;

import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuditAssetProcurementCommandHandler implements CommandHandler<AuditAssetProcurementCommand> {
    private final AuditAssetProcurementRepository auditAssetProcurementRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AuditAssetProcurementCommand command) {
        AuditAssetProcurementModel model = auditAssetProcurementRepository.findByIdOrError(command.getProcurementId());
        return model.handle(eventQueue, command);
    }

}
