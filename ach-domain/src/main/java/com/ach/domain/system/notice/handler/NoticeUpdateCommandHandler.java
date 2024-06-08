package com.ach.domain.system.notice.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.notice.NoticeModel;
import com.ach.domain.system.notice.NoticeRepository;
import com.ach.domain.system.notice.command.UpdateNoticeCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class NoticeUpdateCommandHandler implements CommandHandler<UpdateNoticeCommand> {
    @Resource
    NoticeRepository noticeRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateNoticeCommand command) {
        NoticeModel model = noticeRepository.findByIdOrError(command.getNoticeId());
        return model.handler(eventQueue, command);
    }

}
