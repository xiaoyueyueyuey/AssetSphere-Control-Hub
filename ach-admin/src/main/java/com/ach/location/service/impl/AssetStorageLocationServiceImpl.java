package com.ach.location.service.impl;


import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.mapper.AssetStorageLocationMapper;
import com.ach.location.service.IAssetStorageLocationService;
import com.ach.location.vo.LocationVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Service
public class AssetStorageLocationServiceImpl extends ServiceImpl<AssetStorageLocationMapper, AssetStorageLocationEntity> implements IAssetStorageLocationService {

    @Override

    public List<LocationVO> getLocationList() {
        try {
            return this.baseMapper.selectLocationList();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.Internal.DB_INTERNAL_ERROR);
        }

    }
}
