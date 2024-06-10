package com.ach.asset.domain;

import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.asset.mapper.AssetClassificationMapper;
import com.ach.domain.asset.classification.AssetClassificationModel;
import com.ach.domain.asset.classification.AssetClassificationRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetClassificationRepositoryImpl implements AssetClassificationRepository {
    public final AssetClassificationMapper mapper;

    @Override
    public Boolean checkACNameIsUnique(String acName) {
        return !mapper.exists(new LambdaQueryWrapper<AssetClassificationEntity>().eq(AssetClassificationEntity::getAcName, acName));
    }

    @Override
    public Boolean checkACNameIsUnique(String acName, Integer excludeId) {
        return !mapper.exists(new LambdaQueryWrapper<AssetClassificationEntity>().eq(AssetClassificationEntity::getAcName, acName).ne(AssetClassificationEntity::getAcId, excludeId));
    }

    @Override
    public AssetClassificationModel findByIdOrError(Long id) {
        AssetClassificationEntity assetClassificationEntity = mapper.selectById(id);
        if (assetClassificationEntity == null) {
            return new AssetClassificationModel();
        }
        AssetClassificationModel model = new AssetClassificationModel();
        BeanUtils.copyProperties(assetClassificationEntity, model);
        return model;
    }

    @Override
    public Long save(AssetClassificationModel model) {
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return null;
    }
}
