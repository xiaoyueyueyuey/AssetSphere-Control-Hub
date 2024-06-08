package com.ach.domain.system.post.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class PostDeleteEvent implements DomainEvent {

    private Long postId;
}
