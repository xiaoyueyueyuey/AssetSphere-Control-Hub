package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
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
    @TableId("asset_receipt_id")
    private Long assetReceiptId;

    /**
     * 入库人
     */
    private Long userId;

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 存放房间id
     */
    private Integer roomId;

    /**
     * 逻辑删除
     */
    private Byte deleted;

}
