package com.ach.asset.mapper;

import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.asset.vo.ACIdAndNameVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface AssetClassificationMapper extends BaseMapper<AssetClassificationEntity> {

    List<ACIdAndNameVO> selectACList();
}
