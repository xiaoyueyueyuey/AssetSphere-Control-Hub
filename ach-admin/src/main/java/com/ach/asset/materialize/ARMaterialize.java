package com.ach.asset.materialize;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.entity.AssetReceiptRecordEntity;
import com.ach.asset.mapper.AssetClassificationMapper;
import com.ach.asset.mapper.AssetMapper;
import com.ach.asset.mapper.AssetReceiptRecordMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.asset.receipt.ARAddEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 入库事件监听器
 */

@Component
@RequiredArgsConstructor
public class ARMaterialize implements DomainEventListener {
    private final AssetReceiptRecordMapper receiptMapper;
    private final AssetMapper assetMapper;
    private final AssetClassificationMapper acMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof ARAddEvent) {
            addAR((ARAddEvent) event);
        }

    }

    private void addAR(ARAddEvent event) {
        AssetReceiptRecordEntity assetReceiptRecordEntity = new AssetReceiptRecordEntity();
        BeanUtils.copyProperties(event, assetReceiptRecordEntity);
        int insert = receiptMapper.insert(assetReceiptRecordEntity);
        if (insert > 0) {
            //开始插入资产
            List<AssetEntity> assets = new ArrayList<>();
//            获得资产分类名
            String acName = acMapper.selectById(event.getAcId()).getAcName();
            for (int i = 0; i < event.getCount(); i++) {
                AssetEntity assetEntity = new AssetEntity();
                assetEntity.setAcId(event.getAcId());
                assetEntity.setAcName(acName);
                assetEntity.setAssetName(event.getAssetName());
                assetEntity.setAssetNumber(UUID.randomUUID().toString());//每个资产都有一个唯一的编号
                assetEntity.setAssetModel(event.getAssetModel());
                assetEntity.setRoomId(event.getRoomId());
                assets.add(assetEntity);
            }
            assetMapper.batchInsert(assets);
        }

    }

}
