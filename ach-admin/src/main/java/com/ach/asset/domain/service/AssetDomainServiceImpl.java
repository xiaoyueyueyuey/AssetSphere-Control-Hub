package com.ach.asset.domain.service;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.mapper.AssetMapper;
import com.ach.domain.asset.lending.AssetDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetDomainServiceImpl implements AssetDomainService {
    /**
     * 返回资产，把它从借出状态改为空闲状态
     *
     * @param assetId
     */
    private final AssetMapper assetMapper;

    @Override
    public void returnAsset(Long assetId) {
        AssetEntity assetEntity = new AssetEntity();
        assetEntity.setAssetId(assetId);
        assetEntity.setStatus((byte) 0);
        assetMapper.updateById(assetEntity);
    }
}
