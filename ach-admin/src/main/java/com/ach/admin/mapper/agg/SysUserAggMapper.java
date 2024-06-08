package com.ach.admin.mapper.agg;


import com.ach.admin.entity.agg.SysUserAggEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserAggMapper extends BaseMapper<SysUserAggEntity> {
    String getPasswordByUserId(@Param("userId") Long userId);
}
