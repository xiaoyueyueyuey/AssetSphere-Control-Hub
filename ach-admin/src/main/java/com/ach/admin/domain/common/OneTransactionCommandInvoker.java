package com.ach.admin.domain.common;


import com.ach.common.exception.ApiException;
import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class OneTransactionCommandInvoker implements CommandInvoker {
    private final DomainEventDispatcher domainEventDispatcher;

    @Override
    public <T> T invoke(Function<EventQueue, T> run) {
        SimpleEventQueue simpleEventQueue = new SimpleEventQueue();
        T result = run.apply(simpleEventQueue);
        //等result有结果后才会执行，也就是命令处理完，分发事件给simpleEventQueue的时候
        domainEventDispatcher.dispatchNow(simpleEventQueue);
        return result;
    }

    @Transactional
    @Override
    public <T extends Command> Boolean execute(CommandHandler<T> commandHandler, T command) {
        return this.invoke(eventQueue -> {
            try {
                return commandHandler.handle(eventQueue, command);
            } catch (ApiException e) {
                // 记录异常日志
                log.error("Error during command handling", e);
                // 回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw e;
            }
        });
    }

}
