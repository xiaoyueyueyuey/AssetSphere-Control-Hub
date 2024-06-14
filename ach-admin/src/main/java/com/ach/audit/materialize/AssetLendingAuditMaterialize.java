package com.ach.audit.materialize;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.audit.entity.AssetLendingAuditEntity;
import com.ach.audit.mapper.AssetLendingAuditMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.audit.asset.lending.AssetLendingAuditEvent;
import com.ach.infrastructure.user.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class AssetLendingAuditMaterialize implements DomainEventListener {
    private final AssetLendingRecordMapper assetLendingRecordMapper;
    private final AssetLendingAuditMapper assetLendingAuditMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetLendingAuditEvent) {
            audit((AssetLendingAuditEvent) event);
        }
    }

    private void audit(AssetLendingAuditEvent event) {
        AssetLendingRecordEntity assetLendingRecordEntity = new AssetLendingRecordEntity();
        assetLendingRecordEntity.setLendingId(event.getLendingId());
        assetLendingRecordEntity.setAuditStatus(event.getAuditStatus());
        assetLendingRecordMapper.updateById(assetLendingRecordEntity);
        AssetLendingAuditEntity assetLendingAuditEntity = new AssetLendingAuditEntity();
        assetLendingAuditEntity.setLendingId(event.getLendingId());
        assetLendingAuditEntity.setAuditUserId(AuthenticationUtils.getUserId());
        assetLendingAuditEntity.setAuditTime(LocalDateTime.now());
        assetLendingAuditMapper.updateById(assetLendingAuditEntity);
    }
}
