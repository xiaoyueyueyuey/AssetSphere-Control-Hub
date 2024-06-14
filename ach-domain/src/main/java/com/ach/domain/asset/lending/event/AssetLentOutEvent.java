package com.ach.domain.asset.lending.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetLentOutEvent implements DomainEvent {
    private Long lendingId;
    private Long assetId;
}
