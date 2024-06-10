package com.ach.asset.service.impl;

import com.ach.asset.entity.AssetEntity;
import com.ach.asset.mapper.AssetMapper;
import com.ach.asset.query.AssetQuery;
import com.ach.asset.service.IAssetService;
import com.ach.asset.vo.AssetVO;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.mapper.AssetStorageRoomMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AssetServiceImpl extends ServiceImpl<AssetMapper, AssetEntity> implements IAssetService {
    private final AssetStorageRoomMapper roomMapper;

    @Override
    public PageCustomDTO<AssetVO> getAssetNav(AssetQuery query) {
        Page<AssetEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<AssetEntity> records = page.getRecords();
        //映射房间名称
        Map<Integer, String> map = new HashMap<>();
        records.forEach(assetEntity -> {
            if (!map.containsKey(assetEntity.getRoomId())) {
                AssetStorageRoomEntity roomEntity = roomMapper.selectById(assetEntity.getRoomId());
                //教学楼+房间
                map.put(assetEntity.getRoomId(), roomEntity.getLocationName() + roomEntity.getRoomName());
            }
        });
        List<AssetVO> assetVOS = records.stream().map(assetEntity -> {

            AssetVO assetVO = new AssetVO();
            BeanUtils.copyProperties(assetEntity, assetVO);
            assetVO.setRoomName(map.get(assetEntity.getRoomId()));
            return assetVO;
        }).toList();
        return new PageCustomDTO<>(assetVOS, page.getTotal());

    }
}
