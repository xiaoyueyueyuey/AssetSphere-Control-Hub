package com.ach.asset.service;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.query.AssetQuery;
import com.ach.asset.vo.AssetVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
public interface IAssetService extends IService<AssetEntity> {

    PageCustomDTO<AssetVO> getAssetNav(AssetQuery query);
}
