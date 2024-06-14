package com.ach.asset.mapper;

import com.ach.asset.entity.AssetEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Mapper
public interface AssetMapper extends BaseMapper<AssetEntity> {

    void batchInsert(@Param("assets") List<AssetEntity> assets, @Param("time") LocalDateTime time);
}
