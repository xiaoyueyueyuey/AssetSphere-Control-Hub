package com.ach.asset.domain.repository;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.mapper.AssetMapper;
import com.ach.domain.asset.asset.AssetModel;
import com.ach.domain.asset.asset.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {
    private final AssetMapper mapper;

    @Override
    public AssetModel findByIdOrError(Long id) {
        AssetEntity assetEntity = mapper.selectById(id);
        if (assetEntity == null) {
            return new AssetModel();
        }
        AssetModel assetModel = new AssetModel();
        BeanUtils.copyProperties(assetEntity, assetModel);
        return assetModel;
    }

    @Override
    public Long save(AssetModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
