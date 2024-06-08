package com.ach.admin.domain.common;


import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.EventQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 监听类，一旦有聚合完成时，产生事件都会到这里触发，给读模型更新
 */
@RequiredArgsConstructor
@Component
public class SyncDomainEventDispatcher implements DomainEventDispatcher {

    private final List<DomainEventListener> listeners;//这里会自动把所有DomainEventListener的实现类注入进来

    @Override
    public void dispatchNow(EventQueue eventQueue) {
        for (DomainEvent domainEvent : eventQueue.queue()) {
            for (DomainEventListener listener : listeners) {
                listener.onEvent(domainEvent);//每个DomainEventListener实现类都要判断是否可以处理这个事件，在DomainEventListener自行处理
            }
        }

    }
}
