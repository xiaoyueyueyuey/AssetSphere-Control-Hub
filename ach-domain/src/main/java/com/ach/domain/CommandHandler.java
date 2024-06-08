package com.ach.domain;


import com.ach.domain.system.Command;

public interface CommandHandler<T extends Command> {
    Boolean handle(EventQueue eventQueue, T command);
}