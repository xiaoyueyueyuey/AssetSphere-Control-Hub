package com.ach.asset.query;

import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.infrastructure.page.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ACQuery extends AbstractPageQuery<AssetClassificationEntity> {
    private String acName;

    @Override
    public QueryWrapper<AssetClassificationEntity> addQueryCondition() {
        QueryWrapper<AssetClassificationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(acName != null, "ac_name", acName);
        return queryWrapper;
    }
}
