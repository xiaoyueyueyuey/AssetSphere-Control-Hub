package com.ach.domain.location.room.event;

import com.ach.domain.DomainEvent;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RoomUpdateEvent implements DomainEvent {
    private Integer roomId;
    private String roomName;
    private Integer locationId;
    private String remark;
    private Integer roomSort;
    private Boolean isChangeLocation;
}
