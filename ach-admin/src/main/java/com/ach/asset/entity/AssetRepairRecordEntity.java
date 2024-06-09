package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Data
@TableName("asset_repair_record")
public class AssetRepairRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报修id
     */
    @TableId(value = "repair_id", type = IdType.AUTO)
    private Long repairId;

    /**
     * 申请人id
     */
    private Byte userId;

    /**
     * 资产id
     */
    private Long assetId;

    /**
     * 报修金额
     */
    private BigDecimal money;

    /**
     * 备注
     */
    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未维修，1正在维修，2维修成功
     */
    private Byte repairStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

}
