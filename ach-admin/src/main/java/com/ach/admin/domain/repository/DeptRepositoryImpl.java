package com.ach.admin.domain.repository;


import com.ach.admin.entity.agg.SysDeptAggEntity;
import com.ach.admin.mapper.agg.SysDeptAggMapper;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.system.dept.DeptModel;
import com.ach.domain.system.dept.DeptRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DeptRepositoryImpl implements DeptRepository {

    private final SysDeptAggMapper mapper;

    @Override
    public DeptModel findByIdOrError(Long id) {
        SysDeptAggEntity sysDeptAggEntity = mapper.selectById(id);
        if (sysDeptAggEntity == null) {
            return new DeptModel();
        }
        DeptModel deptModel = new DeptModel();
        BeanUtils.copyProperties(sysDeptAggEntity, deptModel);
        return deptModel;
    }

    @Override
    public Long save(DeptModel model) {
        SysDeptAggEntity sysDeptAggEntity = new SysDeptAggEntity();
        if (model.getDeptId() == null) {
            int insert = mapper.insert(sysDeptAggEntity);
            if (insert > 0) {
                //返回新增的主键
                return sysDeptAggEntity.getDeptId();
            }
        } else {
            return (long) mapper.updateById(sysDeptAggEntity);
        }
        throw new ApiException(ErrorCode.Internal.INTERNAL_ERROR);


    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return mapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean checkDeptNameIsUnique(String deptName) {
        return mapper.exists(new LambdaQueryWrapper<SysDeptAggEntity>().eq(SysDeptAggEntity::getDeptName, deptName));
    }

    @Override
    public Boolean checkDeptNameIsUnique(String deptName, Long deptId) {
        return mapper.exists(new LambdaQueryWrapper<SysDeptAggEntity>().eq(SysDeptAggEntity::getDeptName, deptName).ne(SysDeptAggEntity::getDeptId, deptId));
    }

    @Override
    public Boolean checkParentDeptIsExist(Long parentId) {
        if (parentId == 0) {
            return true;
        }
        return mapper.exists(new LambdaQueryWrapper<SysDeptAggEntity>().eq(SysDeptAggEntity::getDeptId, parentId));
    }

//    @Override
//    public Integer getChildDeptCount(Long deptId) {
//
//        return mapper);
//    }
//
//    @Override
//    public Integer getUserIsAssignedCount(Long deptId) {
//        return null;
//    }
}
