package com.ach.location.domain.serviceImpl;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.mapper.AssetMapper;
import com.ach.domain.location.room.RoomService;
import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.mapper.AssetStorageLocationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final AssetStorageLocationMapper locationMapper;
    private final AssetMapper assetMapper;
    @Override
    public Boolean checkLocationIdIsExist(Integer locationId) {
        return locationMapper.exists(new LambdaQueryWrapper<>(AssetStorageLocationEntity.class).eq(AssetStorageLocationEntity::getLocationId, locationId));
    }

    @Override
    public Boolean checkHasAsset(Integer roomId) {
        return assetMapper.exists(new QueryWrapper<>(AssetEntity.class).eq("room_id", roomId));
    }
}
