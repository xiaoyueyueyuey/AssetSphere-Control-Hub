package com.ach.location.query;

import com.ach.infrastructure.page.AbstractPageQuery;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoomQuery  extends AbstractPageQuery<AssetStorageRoomEntity> {

    private Integer locationId;
    private String roomName;


    @Override
    public QueryWrapper<AssetStorageRoomEntity> addQueryCondition() {
        QueryWrapper<AssetStorageRoomEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(locationId != null, "location_id", locationId).like(roomName != null, "room_name", roomName);
        return queryWrapper;
    }
}
