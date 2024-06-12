package com.ach.admin.mapper;


import com.ach.admin.entity.SysConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 *
 * @since 2022-06-09
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {

    Set<String> selectConfigOptionSet(@Param("configId") Integer configId);
}
