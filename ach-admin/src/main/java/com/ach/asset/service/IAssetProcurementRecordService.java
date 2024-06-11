package com.ach.asset.service;


import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.query.APQuery;
import com.ach.asset.vo.ARVO;
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
public interface IAssetProcurementRecordService extends IService<AssetProcurementRecordEntity> {

    PageCustomDTO<ARVO> getAPNav(APQuery query);
}
