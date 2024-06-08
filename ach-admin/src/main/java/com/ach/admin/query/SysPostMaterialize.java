package com.ach.admin.query;

import com.ach.admin.entity.SysPostEntity;
import com.ach.admin.mapper.SysPostMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.post.event.PostAddEvent;
import com.ach.domain.system.post.event.PostDeleteEvent;
import com.ach.domain.system.post.event.PostUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class SysPostMaterialize implements DomainEventListener {
    final SysPostMapper sysPostMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof PostAddEvent) addSysPost((PostAddEvent) event);
        else if (event instanceof PostUpdateEvent) updateSysPost((PostUpdateEvent) event);
        else if (event instanceof PostDeleteEvent) deleteSysPost((PostDeleteEvent) event);
    }

    private void deleteSysPost(PostDeleteEvent event) {
        sysPostMapper.deleteById(event.getPostId());
    }

    private void updateSysPost(PostUpdateEvent event) {
        SysPostEntity sysPostEntity = new SysPostEntity();
        BeanUtils.copyProperties(event, sysPostEntity);
        sysPostMapper.updateById(sysPostEntity);
    }

    private void addSysPost(PostAddEvent event) {
        SysPostEntity sysPostEntity = new SysPostEntity();
        BeanUtils.copyProperties(event, sysPostEntity);
        sysPostMapper.insert(sysPostEntity);

    }
}
