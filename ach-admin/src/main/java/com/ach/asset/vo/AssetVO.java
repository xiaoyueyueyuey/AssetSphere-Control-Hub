package com.ach.asset.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssetVO {
    private Long assetId;
    private Integer acId;
    private String assetNumber;
    private String acName;
    private String assetName;
    private String assetModel;
    private Integer roomId;
    private String roomName;
    private String remark;
    private Byte status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
