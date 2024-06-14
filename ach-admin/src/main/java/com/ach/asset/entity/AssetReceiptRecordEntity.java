package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("asset_receipt_record")
public class AssetReceiptRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 入库id
     */
    @TableId(value = "asset_receipt_id", type = IdType.AUTO)
    private Long assetReceiptId;
    /**
     * 资产分类
     */
    private Integer acId;
    /**
     * 资产名
     */
    private String assetName;
    /**
     * 资产型号
     */
    private String assetModel;
    /**
     * 存放房间id
     */
    private Integer roomId;
    /**
     * 入库数量
     */
    private Long count;
    /**
     * 单价
     */
    private BigDecimal unitMoney;
    /**
     * 总价
     */
    private BigDecimal totalMoney;
    /**
     * 备注
     */
    private String remark;

    @Schema(description = "创建者ID")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Long creatorId;
    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @Schema(description = "更新者ID")
    @TableField(value = "updater_id", fill = FieldFill.UPDATE, updateStrategy = FieldStrategy.NOT_NULL)
    private Long updaterId;
    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * deleted字段请在数据库中 设置为tinyInt   并且非null   默认值为0
     */
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
