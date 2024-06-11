package com.ach.asset.domain.repository;


import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.domain.asset.lending.AssetLendingModel;
import com.ach.domain.asset.lending.AssetLendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetLendingRepositoryImpl implements AssetLendingRepository {
    private final AssetLendingRecordMapper mapper;

    @Override
    public AssetLendingModel findByIdOrError(Long id) {

        AssetLendingRecordEntity assetLendingRecordEntity = mapper.selectById(id);
        if (assetLendingRecordEntity == null) {
            return new AssetLendingModel();
        }
        AssetLendingModel assetLendingModel = new AssetLendingModel();
        BeanUtils.copyProperties(assetLendingRecordEntity, assetLendingModel);
        return assetLendingModel;
    }

    @Override
    public Long save(AssetLendingModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
