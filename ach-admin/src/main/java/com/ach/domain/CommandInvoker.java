package com.ach.domain;



import java.util.function.Function;

public interface CommandInvoker {
    <R> R invoke(Function<EventQueue, R> run);

    <T extends Command> Boolean execute(CommandHandler<T> commandHandler, T command);
}
