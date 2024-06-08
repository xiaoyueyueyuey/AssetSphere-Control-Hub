package com.ach.domain.system.notice.event;

import lombok.Data;

@Data
public class NoticeUpdateEvent {
    protected String noticeTitle;
    protected Integer noticeType;
    protected String noticeContent;
    protected Integer status;
    protected String remark;
    private Long noticeId;

}
