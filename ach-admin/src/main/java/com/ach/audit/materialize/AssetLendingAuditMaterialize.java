package com.ach.audit.materialize;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.asset.mapper.AssetMapper;
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
    private final AssetMapper assetMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetLendingAuditEvent) {
            audit((AssetLendingAuditEvent) event);
        }
    }

    private void audit(AssetLendingAuditEvent event) {
        // 创建一个新的AssetLendingRecordEntity实例，并设置借用ID和审核状态
        AssetLendingRecordEntity assetLendingRecordEntity = new AssetLendingRecordEntity();
        assetLendingRecordEntity.setLendingId(event.getLendingId());
        assetLendingRecordEntity.setAuditStatus(event.getAuditStatus());
        // 更新借用记录表中的记录
        assetLendingRecordMapper.updateById(assetLendingRecordEntity);
        // 创建一个新的AssetLendingAuditEntity实例，并设置借用ID、审核用户ID和审核时间
        AssetLendingAuditEntity assetLendingAuditEntity = new AssetLendingAuditEntity();
        assetLendingAuditEntity.setLendingId(event.getLendingId());
        assetLendingAuditEntity.setAuditUserId(AuthenticationUtils.getUserId());
        assetLendingAuditEntity.setAuditTime(LocalDateTime.now());
        // 更新借用审核表中的记录
        assetLendingAuditMapper.updateById(assetLendingAuditEntity);

    }
}
