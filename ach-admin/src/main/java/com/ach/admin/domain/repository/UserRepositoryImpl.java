package com.ach.admin.domain.repository;

import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.entity.agg.SysDeptAggEntity;
import com.ach.admin.entity.agg.SysPostAggEntity;
import com.ach.admin.entity.agg.SysUserAggEntity;
import com.ach.admin.mapper.SysRoleMapper;
import com.ach.admin.mapper.agg.SysDeptAggMapper;
import com.ach.admin.mapper.agg.SysPostAggMapper;
import com.ach.admin.mapper.agg.SysUserAggMapper;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.UserProfileModel;
import com.ach.domain.system.user.UserRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {
    private final SysUserAggMapper sysUserAggMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysDeptAggMapper sysDeptAggMapper;
    private final SysPostAggMapper sysPostAggMapper;

    @Override
    public UserModel findByIdOrError(Long id) {
        SysUserAggEntity sysUserAggEntity = sysUserAggMapper.selectById(id);
        if (sysUserAggEntity == null) {
            return new UserModel();
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(sysUserAggEntity, userModel);
        return userModel;
    }
    @Override
    public Long save(UserModel model) {
        SysUserAggEntity sysUserAggEntity = new SysUserAggEntity();
        BeanUtils.copyProperties(model, sysUserAggEntity);
        if (model.getUserId() == null) {
            int insert = sysUserAggMapper.insert(sysUserAggEntity);
            if (insert > 0) {
                return sysUserAggEntity.getUserId();
            }
        } else {
            return (long) sysUserAggMapper.updateById(sysUserAggEntity);
        }
        throw new ApiException(ErrorCode.Internal.INTERNAL_ERROR);
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {

        return sysUserAggMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean checkUsernameIsUnique(String username) {

        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getUsername, username));
    }

    @Override
    public Boolean checkUsernameIsUnique(String username, Long excludeUserId) {
        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getUsername, username).ne(SysUserAggEntity::getUserId, excludeUserId));
    }

    @Override
    public Boolean checkEmailIsUnique(String email) {
        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getEmail, email));
    }

    @Override
    public Boolean checkEmailIsUnique(String email, Long excludeUserId) {
        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getEmail, email).ne(SysUserAggEntity::getUserId, excludeUserId));
    }

    @Override
    public Boolean checkPhoneNumberIsUnique(String phoneNumber) {
        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getPhoneNumber, phoneNumber));

    }

    @Override
    public Boolean checkPhoneNumberIsUnique(String phoneNumber, Long excludeUserId) {
        return !sysUserAggMapper.exists(new LambdaQueryWrapper<SysUserAggEntity>().eq(SysUserAggEntity::getPhoneNumber, phoneNumber).ne(SysUserAggEntity::getUserId, excludeUserId));
    }

    @Override
    public Boolean checkDeptIsExist(Long deptId) {
        return sysDeptAggMapper.exists(new LambdaQueryWrapper<SysDeptAggEntity>().eq(SysDeptAggEntity::getDeptId, deptId));
    }

    @Override
    public Boolean checkRoleIsExist(Long roleId) {
        //TODO 到时要改成角色聚合根
        return sysRoleMapper.exists(new LambdaQueryWrapper<SysRoleEntity>().eq(SysRoleEntity::getRoleId, roleId));
    }

    @Override
    public Boolean checkPostIsExist(Long postId) {
        return sysPostAggMapper.exists(new LambdaQueryWrapper<SysPostAggEntity>().eq(SysPostAggEntity::getPostId, postId));
    }

    @Override
    public String getPasswordByUserId(Long userId) {
        return sysUserAggMapper.getPasswordByUserId(userId);
    }

    @Override
    public Boolean save(UserProfileModel model) {
        return null;
    }
}
