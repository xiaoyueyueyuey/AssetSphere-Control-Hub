package com.ach.asset.service;

import com.ach.asset.entity.AssetReceiptRecordEntity;
import com.ach.asset.query.ARQuery;
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
public interface IAssetReceiptRecordService extends IService<AssetReceiptRecordEntity> {

    PageCustomDTO<ARVO> getARNav(ARQuery query);
}
