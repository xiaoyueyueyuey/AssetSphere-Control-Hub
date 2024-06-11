package com.ach.asset.materialize;

import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.mapper.AssetProcurementRecordMapper;
import com.ach.audit.entity.AssetProcurementAuditEntity;
import com.ach.audit.mapper.AssetProcurementAuditMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.asset.procurement.event.AssetApplyForProcureEvent;
import com.ach.domain.asset.procurement.event.AssetProcurementCancelEvent;
import com.ach.domain.asset.procurement.event.AssetProcurementStatusChangeEvent;
import com.ach.infrastructure.user.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class APMaterialize implements DomainEventListener {
    private final AssetProcurementRecordMapper assetProcurementMapper;
    private final AssetProcurementAuditMapper assetProcurementAuditMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetApplyForProcureEvent) {
            applyForProcure((AssetApplyForProcureEvent) event);
        } else if (event instanceof AssetProcurementCancelEvent) {
            cancelProcurement((AssetProcurementCancelEvent) event);
        } else if (event instanceof AssetProcurementStatusChangeEvent) {
            changeProcurementStatus((AssetProcurementStatusChangeEvent) event);
        }
    }

    private void applyForProcure(AssetApplyForProcureEvent event) {
        AssetProcurementRecordEntity apEntity = new AssetProcurementRecordEntity();
        BeanUtils.copyProperties(event, apEntity);
        apEntity.setAuditStatus((byte) 0);
        apEntity.setUserId(AuthenticationUtils.getUserId());
        assetProcurementMapper.insert(apEntity);
        Long procurementId = apEntity.getProcurementId();
        AssetProcurementAuditEntity assetProcurementAuditEntity = new AssetProcurementAuditEntity();
        assetProcurementAuditEntity.setProcurementId(procurementId);
        assetProcurementAuditMapper.insert(assetProcurementAuditEntity);
    }


    private void cancelProcurement(AssetProcurementCancelEvent event) {
        AssetProcurementRecordEntity assetProcurementRecordEntity = new AssetProcurementRecordEntity();
        assetProcurementRecordEntity.setProcurementId(event.getProcurementId());
        assetProcurementRecordEntity.setStatus(true);
        assetProcurementMapper.updateById(assetProcurementRecordEntity);

    }


    private void changeProcurementStatus(AssetProcurementStatusChangeEvent event) {
        AssetProcurementRecordEntity assetProcurementRecordEntity = new AssetProcurementRecordEntity();

        assetProcurementRecordEntity.setProcurementStatus(event.getProcurementStatus());
        assetProcurementRecordEntity.setProcurementId(event.getProcurementId());
        assetProcurementMapper.updateById(assetProcurementRecordEntity);
    }
}
