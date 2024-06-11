package com.ach.domain.asset.lending.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetReturnEvent implements DomainEvent {
    private Long lendingId;
    private Long assetId;

}
