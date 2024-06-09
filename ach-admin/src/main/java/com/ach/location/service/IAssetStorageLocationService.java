package com.ach.location.service;

import com.ach.location.entity.AssetStorageLocationEntity;
import com.ach.location.vo.LocationVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
public interface IAssetStorageLocationService extends IService<AssetStorageLocationEntity> {

    List<LocationVO> getLocationList();
}
