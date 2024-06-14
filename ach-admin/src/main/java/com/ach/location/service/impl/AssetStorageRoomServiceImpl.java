package com.ach.location.service.impl;

import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.mapper.AssetStorageLocationMapper;
import com.ach.location.mapper.AssetStorageRoomMapper;
import com.ach.location.query.RoomQuery;
import com.ach.location.vo.RoomVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@RequiredArgsConstructor
@Service
public class AssetStorageRoomServiceImpl extends ServiceImpl<AssetStorageRoomMapper, AssetStorageRoomEntity> implements IAssetStorageRoomService {

    private final AssetStorageLocationMapper assetStorageLocationMapper;
    @Override
    public PageCustomDTO<RoomVO> getRoomNav(RoomQuery query) {
        Page<AssetStorageRoomEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<RoomVO> records=page.getRecords().stream().map(RoomVO::new).toList();
        return  new PageCustomDTO<>(records,page.getTotal());
    }

    @Override
    public List<RoomVO> getLocationAndRoomTree() {
        List<AssetStorageRoomEntity> assetStorageRoomEntities = this.baseMapper.selectList(null);
        List<AssetStorageLocationEntity> assetStorageLocationEntities = assetStorageLocationMapper.selectList(null);
        return assetStorageRoomEntities.stream().map(roomEntity -> {
            RoomVO roomVO = new RoomVO(roomEntity);
            AssetStorageLocationEntity locationEntity = assetStorageLocationEntities.stream()
                    .filter(location -> location.getLocationId().equals(roomEntity.getLocationId()))
                    .findFirst().orElse(null);
            roomVO.setLocationName(locationEntity == null ? "" : locationEntity.getLocationName());
            return roomVO;
        }).toList();
    }
}
