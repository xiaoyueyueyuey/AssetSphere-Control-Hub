package com.ach.asset.service;


import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.asset.query.ACQuery;
import com.ach.asset.vo.ACVO;
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
public interface IAssetClassificationService extends IService<AssetClassificationEntity> {

    PageCustomDTO<ACVO> getACNav(ACQuery query);
}
