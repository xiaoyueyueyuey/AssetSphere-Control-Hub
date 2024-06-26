package com.ach.domain.system.log.login;


import com.ach.domain.EventQueue;
import com.ach.domain.system.log.login.command.AddLoginLogCommand;
import com.ach.domain.system.log.login.command.DeleteLoginLogCommand;
import com.ach.domain.system.log.login.event.LoginLogAddEvent;
import com.ach.domain.system.log.login.event.LoginLogDeleteEvent;
import org.springframework.beans.BeanUtils;

public class LoginLogModel {
    /**
     * 删除登录日志
     *
     * @param eventQueue
     * @param command
     * @return
     */
    public Boolean handle(EventQueue eventQueue, DeleteLoginLogCommand command) {
        LoginLogDeleteEvent loginLogDeleteEvent = new LoginLogDeleteEvent();
        loginLogDeleteEvent.setInfoIds(command.getInfoIds());
        eventQueue.enqueue(loginLogDeleteEvent);
        return true;
    }

    public Boolean handle(EventQueue eventQueue, AddLoginLogCommand command) {
        LoginLogAddEvent loginLogAddEvent = new LoginLogAddEvent();
        BeanUtils.copyProperties(command, loginLogAddEvent);
        eventQueue.enqueue(loginLogAddEvent);
        return true;
    }

}
