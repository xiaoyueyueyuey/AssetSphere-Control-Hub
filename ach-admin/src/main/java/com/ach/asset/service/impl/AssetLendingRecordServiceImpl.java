package com.ach.asset.service.impl;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.asset.query.AssetLendingQuery;
import com.ach.asset.service.IAssetLendingRecordService;
import com.ach.asset.vo.AssetLendingVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@RequiredArgsConstructor
@Service
public class AssetLendingRecordServiceImpl extends ServiceImpl<AssetLendingRecordMapper, AssetLendingRecordEntity> implements IAssetLendingRecordService {

    @Override
    public PageCustomDTO<AssetLendingVO> getALNav(AssetLendingQuery query) {

        Page<AssetLendingVO> page = new Page<>(query.getPageSize(), query.getPageNum());
        this.baseMapper.selectALNav(page, query.getUserId(), query.getUserName(), query.getReturnStatus(), query.getAuditStatus());

        return new PageCustomDTO<>(page.getRecords(), page.getTotal());


    }
}
