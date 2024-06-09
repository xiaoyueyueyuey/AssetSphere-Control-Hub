package com.ach.domain.location.room.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class RoomStatusChangeEvent implements DomainEvent {
    private Integer roomId;
    private Boolean status;
}
