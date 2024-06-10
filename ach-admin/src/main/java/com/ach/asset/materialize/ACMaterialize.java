package com.ach.asset.materialize;

import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.asset.mapper.AssetClassificationMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.asset.classification.event.ACAddEvent;
import com.ach.domain.asset.classification.event.ACDeleteEvent;
import com.ach.domain.asset.classification.event.ACStatusChangeEvent;
import com.ach.domain.asset.classification.event.ACUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ACMaterialize implements DomainEventListener {
    public final AssetClassificationMapper mapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof ACAddEvent) {
            addAC((ACAddEvent) event);
        } else if (event instanceof ACDeleteEvent) {
            deleteAC((ACDeleteEvent) event);
        } else if (event instanceof ACUpdateEvent) {
            updateAC((ACUpdateEvent) event);
        } else if (event instanceof ACStatusChangeEvent) {
            updateACStatus((ACStatusChangeEvent) event);
        }
    }

    private void updateACStatus(ACStatusChangeEvent event) {
        AssetClassificationEntity entity = new AssetClassificationEntity();
        BeanUtils.copyProperties(event, entity);
        mapper.updateById(entity);
    }

    private void updateAC(ACUpdateEvent event) {
        AssetClassificationEntity entity = new AssetClassificationEntity();
        BeanUtils.copyProperties(event, entity);
        mapper.updateById(entity);

    }

    private void deleteAC(ACDeleteEvent event) {
        mapper.deleteById(event.getAcId());
    }

    private void addAC(ACAddEvent event) {
        AssetClassificationEntity entity = new AssetClassificationEntity();
        BeanUtils.copyProperties(event, entity);
        mapper.insert(entity);
    }
}
