package com.ach.domain.asset.asset.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetUpdateEvent implements DomainEvent {
    private Long assetId;
    private Integer acId;
    private String assetName;
    private String assetModel;
    private Integer roomId;
    private String remark;
}
