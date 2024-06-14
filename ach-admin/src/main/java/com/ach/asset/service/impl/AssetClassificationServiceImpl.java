package com.ach.asset.service.impl;


import com.ach.asset.entity.AssetClassificationEntity;
import com.ach.asset.mapper.AssetClassificationMapper;
import com.ach.asset.query.ACQuery;
import com.ach.asset.service.IAssetClassificationService;
import com.ach.asset.vo.ACIdAndNameVO;
import com.ach.asset.vo.ACVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Service
public class AssetClassificationServiceImpl extends ServiceImpl<AssetClassificationMapper, AssetClassificationEntity> implements IAssetClassificationService {

    @Override
    public PageCustomDTO<ACVO> getACNav(ACQuery query) {
        Page<AssetClassificationEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<ACVO> list = page.getRecords().stream().map(ACVO::new).toList();
        return new PageCustomDTO<>(list, page.getTotal());
    }

    @Override
    public List<ACIdAndNameVO> getACList() {

        return this.baseMapper.selectACList();
    }
}
