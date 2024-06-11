package com.ach.asset.service;


import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.query.AssetLendingQuery;
import com.ach.asset.vo.AssetLendingVO;
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
public interface IAssetLendingRecordService extends IService<AssetLendingRecordEntity> {

    PageCustomDTO<AssetLendingVO> getALNav(AssetLendingQuery query);
}
