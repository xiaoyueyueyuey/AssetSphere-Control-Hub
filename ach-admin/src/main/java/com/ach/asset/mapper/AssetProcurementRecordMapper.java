package com.ach.asset.mapper;


import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.vo.ARVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Mapper

public interface AssetProcurementRecordMapper extends BaseMapper<AssetProcurementRecordEntity> {

    Page<ARVO> selectAPNav(@Param("page") Page<ARVO> page,
                           @Param("userId") Long userId, @Param("userName") String userName,
                           @Param("procurementStatus") Boolean procurementStatus);
}
