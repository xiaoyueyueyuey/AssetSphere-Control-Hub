package com.ach.location.mapper;

import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.vo.LocationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
public interface AssetStorageLocationMapper extends BaseMapper<AssetStorageLocationEntity> {

    List<LocationVO> selectLocationList();
}
