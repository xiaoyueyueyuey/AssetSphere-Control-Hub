package com.ach.audit.domain;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.domain.audit.asset.lending.AuditAssetLendingModel;
import com.ach.domain.audit.asset.lending.AuditAssetLendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuditAssetLendingRepositoryImpl implements AuditAssetLendingRepository {
    private final AssetLendingRecordMapper mapper;

    @Override
    public AuditAssetLendingModel findByIdOrError(Long id) {
        AssetLendingRecordEntity assetLendingRecordEntity = mapper.selectById(id);
        if (assetLendingRecordEntity == null) {
            return new AuditAssetLendingModel();
        }
        AuditAssetLendingModel auditAssetLendingModel = new AuditAssetLendingModel();
        BeanUtils.copyProperties(assetLendingRecordEntity, auditAssetLendingModel);
        return auditAssetLendingModel;
    }

    @Override
    public Long save(AuditAssetLendingModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
