package com.ach.admin.domain.common;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.Command;

import java.util.function.Function;

public interface CommandInvoker {
    <R> R invoke(Function<EventQueue, R> run);

    <T extends Command> Boolean execute(CommandHandler<T> commandHandler, T command);
}
