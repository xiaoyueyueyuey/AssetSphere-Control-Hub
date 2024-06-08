package com.ach.domain.system.notice.event;


import com.ach.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoticeDeleteEvent implements DomainEvent {
    private Long noticeId;
}
