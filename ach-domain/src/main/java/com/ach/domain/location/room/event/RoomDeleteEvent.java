package com.ach.domain.location.room.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class RoomDeleteEvent implements DomainEvent {
    private Integer roomId;
}
