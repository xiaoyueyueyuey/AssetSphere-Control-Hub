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
@TableName("asset_procurement_record")
public class AssetProcurementRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购id
     */
    @TableId(value = "procurement_id", type = IdType.AUTO)
    private Long procurementId;

    /**
     * 申请人
     */
    private Long userId;

    /**
     * 采购所需费用
     */
    private BigDecimal money;

    /**
     * 采购内容
     */
    private String remark;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未采购 1正在采购 2采购完成
     */
    private Byte procurementStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

}
