package com.ach.domain.asset.asset.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetDeleteEvent implements DomainEvent {
    private Long assetId;
}
