package com.ach.domain.location.room.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class RoomAddEvent implements DomainEvent {
    private String roomName;
    private Integer locationId;
    private String remark;
    private Integer roomSort;
    private Boolean isChangeLocation;
}
