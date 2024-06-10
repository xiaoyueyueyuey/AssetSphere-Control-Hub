package com.ach.domain.asset.asset.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetStatusChangeEvent implements DomainEvent {
    private Integer assetId;
    private Byte status;
}
