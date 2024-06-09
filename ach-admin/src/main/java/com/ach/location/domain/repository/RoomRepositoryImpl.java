package com.ach.location.domain.repository;

import com.ach.domain.location.room.RoomModel;
import com.ach.domain.location.room.RoomRepository;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.mapper.AssetStorageRoomMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {
    private final AssetStorageRoomMapper mapper;

    @Override
    public RoomModel findByIdOrError(Long id) {
        AssetStorageRoomEntity assetStorageRoomEntity = mapper.selectById(id);
        if (assetStorageRoomEntity == null) {
            return new RoomModel();
        }
        RoomModel model = new RoomModel();
        BeanUtils.copyProperties(assetStorageRoomEntity, model);
        return model;
    }

    @Override
    public Long save(RoomModel model) {
        //事件存储了model的数据
        return null;
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        //事件存储了model的数据
        return null;
    }

    @Override
    public Boolean checkRoomNameIsUnique(String roomName) {
       return !mapper.exists(new LambdaQueryWrapper<>(AssetStorageRoomEntity.class).eq(AssetStorageRoomEntity::getRoomName, roomName));

    }

    @Override
    public Boolean checkRoomNameIsUnique(String roomName, Integer excludeId) {
        return !mapper.exists(new LambdaQueryWrapper<>(AssetStorageRoomEntity.class).eq(AssetStorageRoomEntity::getRoomName, roomName).ne(AssetStorageRoomEntity::getRoomId, excludeId));
    }
}
