package com.ach.asset.service.impl;


import com.ach.asset.entity.AssetProcurementRecordEntity;
import com.ach.asset.mapper.AssetProcurementRecordMapper;
import com.ach.asset.query.APQuery;
import com.ach.asset.service.IAssetProcurementRecordService;
import com.ach.asset.vo.APVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Service
public class AssetProcurementRecordServiceImpl extends ServiceImpl<AssetProcurementRecordMapper, AssetProcurementRecordEntity> implements IAssetProcurementRecordService {

    @Override
    public PageCustomDTO<APVO> getAPNav(APQuery query) {
        Page<APVO> page = new Page<>(query.getPageNum(), query.getPageSize());
        this.baseMapper.selectAPNav(page, query.getUserName(), query.getProcurementStatus(), query.getAuditStatus());
        return new PageCustomDTO<>(page.getRecords(), page.getTotal());
    }
}
