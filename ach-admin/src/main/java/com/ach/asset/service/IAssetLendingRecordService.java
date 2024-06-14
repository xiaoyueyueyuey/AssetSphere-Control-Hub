package com.ach.asset.service;


import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.query.ALQuery;
import com.ach.asset.vo.ALVO;
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

    PageCustomDTO<ALVO> getALNav(ALQuery query);

    PageCustomDTO<ALVO> getSelfALNav(ALQuery query);
}
