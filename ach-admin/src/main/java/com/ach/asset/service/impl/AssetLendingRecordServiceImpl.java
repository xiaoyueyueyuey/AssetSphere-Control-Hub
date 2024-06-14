package com.ach.asset.service.impl;

import com.ach.asset.entity.AssetLendingRecordEntity;
import com.ach.asset.mapper.AssetLendingRecordMapper;
import com.ach.asset.query.ALQuery;
import com.ach.asset.service.IAssetLendingRecordService;
import com.ach.asset.vo.ALVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.infrastructure.user.AuthenticationUtils;
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
    public PageCustomDTO<ALVO> getALNav(ALQuery query) {
        Page<ALVO> page = new Page<>(query.getPageNum(), query.getPageSize());
        this.baseMapper.selectALNav(page, query.getUserName(), query.getReturnStatus(), query.getAuditStatus());
        return new PageCustomDTO<>(page.getRecords(), page.getTotal());
    }

    @Override
    public PageCustomDTO<ALVO> getSelfALNav(ALQuery query) {
        Page<ALVO> page = new Page<>(query.getPageNum(), query.getPageSize());
        this.baseMapper.selectSelfALNav(page, AuthenticationUtils.getUserId(), query.getReturnStatus(), query.getAuditStatus());
        return new PageCustomDTO<>(page.getRecords(), page.getTotal());
    }
}
