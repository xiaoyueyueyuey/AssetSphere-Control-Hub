package com.ach.asset.domain.repository;

import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.mapper.AssetProcurementRecordMapper;
import com.ach.domain.audit.asset.procurement.AuditAssetProcurementModel;
import com.ach.domain.audit.asset.procurement.AuditAssetProcurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuditAssetProcurementRepositoryImpl implements AuditAssetProcurementRepository {
    private final AssetProcurementRecordMapper mapper;

    @Override
    public AuditAssetProcurementModel findByIdOrError(Long id) {
        AssetProcurementRecordEntity assetProcurementRecordEntity = mapper.selectById(id);
        if (assetProcurementRecordEntity == null) {
            return new AuditAssetProcurementModel();
        }
        AuditAssetProcurementModel auditAssetProcurementModel = new AuditAssetProcurementModel();
        BeanUtils.copyProperties(assetProcurementRecordEntity, auditAssetProcurementModel);
        return auditAssetProcurementModel;
    }

    @Override
    public Long save(AuditAssetProcurementModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
