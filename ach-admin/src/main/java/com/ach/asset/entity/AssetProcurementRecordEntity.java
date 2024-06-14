package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.*;
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
     * 采购单价
     */
    private BigDecimal unitMoney;

    /**
     * 采购数量
     */
    private Integer count;

    /**
     * 采购总价
     */
    private BigDecimal totalMoney;

    /**
     * 采购内容
     */
    private String remark;

    /**
     * 申请时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0正在采购 1采购完成
     */
    private Boolean procurementStatus;

    /**
     * 0正常申请 1取消申请
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean status;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

}
