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
        // 创建一个新的AssetProcurementRecordEntity实例，并设置采购ID和审核状态
        AssetProcurementRecordEntity assetProcurementRecordEntity = new AssetProcurementRecordEntity();
        assetProcurementRecordEntity.setProcurementId(event.getProcurementId());
        assetProcurementRecordEntity.setAuditStatus(event.getAuditStatus());
        // 更新采购记录表中的记录
        apMapper.updateById(assetProcurementRecordEntity);
        // 创建一个新的AssetProcurementAuditEntity实例，并设置采购ID、审核时间、审核用户ID和备注
        AssetProcurementAuditEntity assetProcurementAuditEntity = new AssetProcurementAuditEntity();
        assetProcurementAuditEntity.setProcurementId(event.getProcurementId());
        assetProcurementAuditEntity.setAuditTime(LocalDateTime.now());
        assetProcurementAuditEntity.setAuditUserId(AuthenticationUtils.getUserId());
        assetProcurementAuditEntity.setRemark(event.getRemark());
        // 更新采购审核表中的记录
        apaMapper.updateById(assetProcurementAuditEntity);
    }
}
