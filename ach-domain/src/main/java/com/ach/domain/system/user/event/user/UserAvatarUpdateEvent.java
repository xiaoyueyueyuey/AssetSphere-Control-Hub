package com.ach.domain.system.user.event.user;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class UserAvatarUpdateEvent implements DomainEvent {
    private Long userId;
    private String avatar;
}
