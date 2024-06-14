package com.ach.asset.query;

import com.ach.asset.entity.AssetEntity;
import com.ach.infrastructure.page.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AssetQuery extends AbstractPageQuery<AssetEntity> {
    private Integer acId;
    private String assetName;
    private Integer roomId;
    private Byte status;


    @Override
    public QueryWrapper<AssetEntity> addQueryCondition() {
        QueryWrapper<AssetEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(acId != null, "ac_id", acId).like(assetName != null, "asset_name", assetName)
                .eq(roomId != null, "room_id", roomId)
                .eq(status != null, "status", status)
        ;
        return queryWrapper;
    }
}
