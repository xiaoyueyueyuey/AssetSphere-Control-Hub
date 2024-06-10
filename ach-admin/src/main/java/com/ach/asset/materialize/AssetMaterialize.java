package com.ach.asset.materialize;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.mapper.AssetMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.asset.asset.event.AssetDeleteEvent;
import com.ach.domain.asset.asset.event.AssetStatusChangeEvent;
import com.ach.domain.asset.asset.event.AssetUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetMaterialize implements DomainEventListener {

    private final AssetMapper assetMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof AssetStatusChangeEvent) {
            changeStatus((AssetStatusChangeEvent) event);
        } else if (event instanceof AssetUpdateEvent) {
            updateAsset((AssetUpdateEvent) event);
        } else if (event instanceof AssetDeleteEvent) {
            deleteAsset((AssetDeleteEvent) event);

        }

    }

    private void deleteAsset(AssetDeleteEvent event) {
        assetMapper.deleteById(event.getAssetId());
    }


    private void updateAsset(AssetUpdateEvent event) {
        AssetEntity assetEntity = new AssetEntity();
        BeanUtils.copyProperties(event, assetEntity);
        assetMapper.updateById(assetEntity);
    }

    private void changeStatus(AssetStatusChangeEvent event) {
        AssetEntity assetEntity = new AssetEntity();
        BeanUtils.copyProperties(event, assetEntity);
        assetMapper.updateById(assetEntity);
    }
}
