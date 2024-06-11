package com.ach.audit.materialize;

import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.mapper.AssetProcurementRecordMapper;
import com.ach.audit.entity.AssetProcurementAuditEntity;
import com.ach.audit.mapper.AssetProcurementAuditMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.audit.asset.procurement.AssetProcurementAuditEvent;
import com.ach.infrastructure.user.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AssetProcurementAuditMaterialize implements DomainEventListener {
    private final AssetProcurementAuditMapper apaMapper;
    private final AssetProcurementRecordMapper apMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetProcurementAuditEvent) {
            audit((AssetProcurementAuditEvent) event);
        }

    }

    private void audit(AssetProcurementAuditEvent event) {
        AssetProcurementRecordEntity assetProcurementRecordEntity = new AssetProcurementRecordEntity();
        assetProcurementRecordEntity.setProcurementId(event.getProcurementId());
        assetProcurementRecordEntity.setAuditStatus(event.getAuditStatus());
        apMapper.updateById(assetProcurementRecordEntity);
        AssetProcurementAuditEntity assetProcurementAuditEntity = new AssetProcurementAuditEntity();
        assetProcurementAuditEntity.setProcurementId(event.getProcurementId());
        assetProcurementAuditEntity.setAuditTime(LocalDateTime.now());
        assetProcurementAuditEntity.setAuditUserId(AuthenticationUtils.getUserId());
        assetProcurementAuditEntity.setRemark(event.getRemark());
        apaMapper.updateById(assetProcurementAuditEntity);

    }
}
