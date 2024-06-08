package com.ach.admin.mapper.agg;


import com.ach.admin.entity.agg.SysPostAggEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysPostAggMapper extends BaseMapper<SysPostAggEntity> {
    Integer selectPostIsAssignedCount(@Param("postId") Long postId);
}
