package com.ach.domain.system.notice.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.notice.NoticeModel;
import com.ach.domain.system.notice.command.AddNoticeCommand;
import org.springframework.stereotype.Component;

@Component
public class NoticeAddCommandHandler implements CommandHandler<AddNoticeCommand> {

    @Override
    public Boolean handle(EventQueue eventQueue, AddNoticeCommand command) {
        //新增就不需要找聚合了，直接new
        NoticeModel noticeModel = new NoticeModel();
        return noticeModel.handler(eventQueue, command);
    }
}
