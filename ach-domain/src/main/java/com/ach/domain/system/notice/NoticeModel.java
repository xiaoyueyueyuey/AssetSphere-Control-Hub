package com.ach.domain.system.notice;


import com.ach.common.enums.BasicEnumUtil;
import com.ach.common.enums.common.NoticeTypeEnum;
import com.ach.common.enums.common.StatusEnum;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.EventQueue;
import com.ach.domain.system.notice.command.AddNoticeCommand;
import com.ach.domain.system.notice.command.DeleteNoticeCommand;
import com.ach.domain.system.notice.command.UpdateNoticeCommand;
import com.ach.domain.system.notice.event.NoticeAddEvent;
import com.ach.domain.system.notice.event.NoticeDeleteEvent;
import com.ach.domain.system.notice.event.NoticeDeleteFailedEvent;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data

public class NoticeModel {
    private Long noticeId;
    private Integer noticeType;
    private Integer status;

    public Boolean handler(EventQueue eventQueue, AddNoticeCommand command) {
        BeanUtils.copyProperties(command, this);//将command的属性拷贝到聚合中
        try {
            checkFields();
        } catch (ApiException e) {
            eventQueue.enqueue(new NoticeDeleteFailedEvent());
            return false;
        }
        //将command的属性拷贝到event中
        NoticeAddEvent noticeAddEvent = new NoticeAddEvent();
        BeanUtils.copyProperties(command, noticeAddEvent);
        eventQueue.enqueue(noticeAddEvent);
        return true;
    }

    public Boolean handler(EventQueue eventQueue, UpdateNoticeCommand command) {
        if (noticeId != null) {
            try {
                checkFields();
            } catch (ApiException e) {
                eventQueue.enqueue(new NoticeDeleteFailedEvent());
                return false;
            }
        } else {
            eventQueue.enqueue(new NoticeDeleteFailedEvent());
            return false;
        }
        eventQueue.enqueue(new NoticeDeleteEvent(command.getNoticeId()));
        return true;
    }

    public Boolean handle(EventQueue eventQueue, DeleteNoticeCommand command) {
        eventQueue.enqueue(new NoticeDeleteEvent(command.getNoticeId()));
        return true;
    }

    /**
     * 检查字段是否合法
     */
    public void checkFields() {
        // 检查枚举值是否合法
        try {
            BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        } catch (ApiException e) {
            throw new ApiException(ErrorCode.Business.NOTICE_TYPE_IS_NOT_EXIST);
        }
        try {
            BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
        } catch (ApiException e) {
            throw new ApiException(ErrorCode.Business.NOTICE_STATUS_IS_NOT_EXIST);
        }


    }

}
