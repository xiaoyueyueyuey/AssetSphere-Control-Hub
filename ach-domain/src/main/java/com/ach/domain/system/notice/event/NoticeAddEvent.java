package com.ach.domain.system.notice.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class NoticeAddEvent implements DomainEvent {
    protected String noticeTitle;
    protected Integer noticeType;
    protected String noticeContent;
    protected Integer status;
    protected String remark;
}
