package com.ach.domain;


public interface CommandHandler<T extends Command> {
    Boolean handle(EventQueue eventQueue, T command);
}