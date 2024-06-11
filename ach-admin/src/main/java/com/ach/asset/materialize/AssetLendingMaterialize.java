package com.ach.asset.materialize;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.audit.entity.AssetLendingAuditEntity;
import com.ach.audit.mapper.AssetLendingAuditMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.asset.lending.AssetDomainService;
import com.ach.domain.asset.lending.event.AssetApplyForLendingEvent;
import com.ach.domain.asset.lending.event.AssetLendingCancelEvent;
import com.ach.domain.asset.lending.event.AssetReturnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetLendingMaterialize implements DomainEventListener {
    private final AssetLendingRecordMapper mapper;
    private final AssetDomainService assetDomainService;
    private final AssetLendingAuditMapper assetLendingAuditMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetApplyForLendingEvent) {
            applyForLending((AssetApplyForLendingEvent) event);
        } else if (event instanceof AssetLendingCancelEvent) {
            cancelLending((AssetLendingCancelEvent) event);
        } else if (event instanceof AssetReturnEvent) {
            returnAsset((AssetReturnEvent) event);
        }
    }

    private void returnAsset(AssetReturnEvent event) {

        AssetLendingRecordEntity assetLendingRecordEntity = new AssetLendingRecordEntity();
        assetLendingRecordEntity.setLendingId(event.getLendingId());
        assetLendingRecordEntity.setReturnStatus(true);//已归还，修改状态
        int updated = mapper.updateById(assetLendingRecordEntity);
        if (updated == 0) {
            //更新失败
            assetDomainService.returnAsset(event.getAssetId());
        }

    }

    private void cancelLending(AssetLendingCancelEvent event) {
        AssetLendingRecordEntity assetLendingRecordEntity = new AssetLendingRecordEntity();
        assetLendingRecordEntity.setLendingId(event.getLendingId());
        assetLendingRecordEntity.setStatus(true);
        mapper.updateById(assetLendingRecordEntity);
    }

    private void applyForLending(AssetApplyForLendingEvent event) {
        AssetLendingRecordEntity assetLendingRecordEntity = new AssetLendingRecordEntity();
        assetLendingRecordEntity.setAuditStatus((byte) 0);
        BeanUtils.copyProperties(event, assetLendingRecordEntity);
        int insert = mapper.insert(assetLendingRecordEntity);
        if (insert == 0) {
            //同步在审核表中插入一条数据
            Long lendingId = assetLendingRecordEntity.getLendingId();
            AssetLendingAuditEntity auditEntity = new AssetLendingAuditEntity();
            auditEntity.setLendingId(lendingId);
            assetLendingAuditMapper.insert(auditEntity);
        }
    }
}
