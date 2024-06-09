package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("asset_lending_record")
public class AssetLendingRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借出id
     */
    @TableId(value = "lending_id", type = IdType.AUTO)
    private Long lendingId;

    /**
     * 借用者
     */
    private Long userId;

    /**
     * 借用资产id
     */
    private Long assetId;

    /**
     * 借出时间
     */
    private LocalDate loanTime;

    /**
     * 归还时间
     */
    private LocalDate returnTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0 待审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未归还,1已归还
     */
    private Byte returnStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

}
