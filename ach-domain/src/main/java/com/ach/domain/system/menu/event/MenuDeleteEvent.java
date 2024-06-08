package com.ach.domain.system.menu.event;


import com.ach.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuDeleteEvent implements DomainEvent {
    private Long menuId;
}
