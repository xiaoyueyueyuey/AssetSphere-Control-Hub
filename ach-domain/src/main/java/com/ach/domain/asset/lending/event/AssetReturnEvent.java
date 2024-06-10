package com.ach.domain.asset.lending.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetReturnEvent implements DomainEvent {
    //    @Positive
    private Long lendingId;
    //    @Positive
    private Long assetId;

}
