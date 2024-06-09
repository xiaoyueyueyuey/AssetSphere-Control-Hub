package com.ach.location.vo;

import com.ach.location.entity.AssetStorageRoomEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RoomVO {
    private Integer roomId;
    private String roomName;
    private Integer locationId;
    private String locationName;
    private Boolean status;
    private String remark;
    private Integer roomSort;
    public RoomVO(AssetStorageRoomEntity entity){
        BeanUtils.copyProperties(entity,this);
    }
}
