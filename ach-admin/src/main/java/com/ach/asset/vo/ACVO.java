package com.ach.asset.vo;

import com.ach.asset.entity.AssetClassificationEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ACVO {
    private Integer acId;
    private String acName;
    private Integer sort;
    private String remark;
    private Boolean status;

    public ACVO(AssetClassificationEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
