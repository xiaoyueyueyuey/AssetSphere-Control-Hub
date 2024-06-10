package com.ach.asset.entity;

import com.ach.infrastructure.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("asset_receipt_record")
public class AssetReceiptRecordEntity extends BaseEntity<AssetReceiptRecordEntity> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 入库id
     */
    @TableId("asset_receipt_id")
    private Long assetReceiptId;

    /**
     * 资产型号
     */
    private String assetModel;
    /**
     * 入库数量
     */
    private Long count;

    /**
     * 备注
     */
    private String remark;

    /**
     * 存放房间id
     */
    private Integer roomId;

    private String assetName;

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    private Integer acId;


}
