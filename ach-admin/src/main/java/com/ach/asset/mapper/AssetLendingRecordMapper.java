package com.ach.asset.mapper;


import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.vo.ALVO;
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
public interface AssetLendingRecordMapper extends BaseMapper<AssetLendingRecordEntity> {

    Page<ALVO> selectALNav(@Param("page") Page<ALVO> page, @Param("userName") String userName,
                           @Param("returnStatus") Boolean returnStatus,
                           @Param("auditStatus") Byte auditStatus);
}
