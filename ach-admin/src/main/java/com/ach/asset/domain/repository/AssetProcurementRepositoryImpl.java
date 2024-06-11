package com.ach.asset.domain.repository;

import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.mapper.AssetProcurementRecordMapper;
import com.ach.domain.asset.procurement.AssetProcurementModel;
import com.ach.domain.asset.procurement.AssetProcurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetProcurementRepositoryImpl implements AssetProcurementRepository {
    private final AssetProcurementRecordMapper mapper;

    @Override
    public AssetProcurementModel findByIdOrError(Long id) {
        AssetProcurementRecordEntity assetProcurementRecordEntity = mapper.selectById(id);
        if (assetProcurementRecordEntity == null) {
            return new AssetProcurementModel();
        }
        AssetProcurementModel assetProcurementModel = new AssetProcurementModel();
        BeanUtils.copyProperties(assetProcurementRecordEntity, assetProcurementModel);
        return assetProcurementModel;
    }

    @Override
    public Long save(AssetProcurementModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
