package com.ach.location.materialize;

import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.location.room.event.RoomAddEvent;
import com.ach.domain.location.room.event.RoomDeleteEvent;
import com.ach.domain.location.room.event.RoomStatusChangeEvent;
import com.ach.domain.location.room.event.RoomUpdateEvent;
import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.mapper.AssetStorageLocationMapper;
import com.ach.location.mapper.AssetStorageRoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoomMaterialize implements DomainEventListener {

    public final AssetStorageRoomMapper mapper;
    public final AssetStorageLocationMapper locationMapper;
    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof RoomAddEvent) {
            addRoom((RoomAddEvent) event);
        } else if (event instanceof RoomUpdateEvent) {
            updateRoom((RoomUpdateEvent) event);
        } else if (event instanceof RoomDeleteEvent) {
            deleteRoom((RoomDeleteEvent) event);
        } else if (event instanceof RoomStatusChangeEvent) {
            changeRoomStatus((RoomStatusChangeEvent) event);
        }

    }

    private void changeRoomStatus(RoomStatusChangeEvent event) {
        AssetStorageRoomEntity entity = new AssetStorageRoomEntity();
        BeanUtils.copyProperties(event, entity);
        mapper.updateById(entity);
    }

    private void deleteRoom(RoomDeleteEvent event) {
        mapper.deleteById(event.getRoomId());
    }

    private void updateRoom(RoomUpdateEvent event) {
        AssetStorageRoomEntity entity = new AssetStorageRoomEntity();
        if(event.getIsChangeLocation()){
            //教学楼改变了，需要改变实体的位置
            AssetStorageLocationEntity locationEntity = locationMapper.selectById(event.getLocationId());
            entity.setLocationName(locationEntity.getLocationName());
        }
        BeanUtils.copyProperties(event, entity);
        mapper.updateById(entity);
    }
    private void addRoom(RoomAddEvent event) {
        AssetStorageRoomEntity entity = new AssetStorageRoomEntity();
        AssetStorageLocationEntity locationEntity = locationMapper.selectById(event.getLocationId());
        BeanUtils.copyProperties(event, entity);
        entity.setLocationName(locationEntity.getLocationName());
        mapper.insert(entity);
    }
}