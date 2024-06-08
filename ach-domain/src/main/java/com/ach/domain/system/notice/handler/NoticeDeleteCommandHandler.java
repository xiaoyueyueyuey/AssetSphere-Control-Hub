package com.ach.domain.system.notice.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.notice.NoticeModel;
import com.ach.domain.system.notice.command.DeleteNoticeCommand;
import org.springframework.stereotype.Component;

@Component
public class NoticeDeleteCommandHandler implements CommandHandler<DeleteNoticeCommand> {
    @Override
    public Boolean handle(EventQueue eventQueue, DeleteNoticeCommand command) {
        NoticeModel noticeModel = new NoticeModel();
        //处理命令
        return noticeModel.handle(eventQueue, command);
    }
}
