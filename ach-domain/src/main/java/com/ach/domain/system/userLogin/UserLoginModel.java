package com.ach.domain.system.userLogin;


import com.ach.domain.EventQueue;
import com.ach.domain.system.userLogin.command.UserLoginCommand;
import com.ach.domain.system.userLogin.event.UserLoginEvent;

public class UserLoginModel {
    public Boolean handle(EventQueue eventQueue, UserLoginCommand command) {
        UserLoginEvent userLoginEvent = new UserLoginEvent();
        eventQueue.enqueue(userLoginEvent);
        return true;
    }
}
