package com.ach.asset.query;


import com.ach.asset.entity.AssetReceiptRecordEntity;
import com.ach.infrastructure.page.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ARQuery extends AbstractPageQuery<AssetReceiptRecordEntity> {
    private String assetName;

    @Override
    public QueryWrapper<AssetReceiptRecordEntity> addQueryCondition() {
        QueryWrapper<AssetReceiptRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(assetName != null, "asset_name", assetName);
        return queryWrapper;
    }
}
