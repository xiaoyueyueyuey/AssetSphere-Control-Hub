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
        // 获取分页结果
        Page<AssetEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<AssetEntity> records = page.getRecords();

        // 映射房间名称
        Map<Integer, String> map = new HashMap<>();
        records.forEach(assetEntity -> {
            // 如果map中不包含该房间ID，则查询房间信息并存入map
            if (!map.containsKey(assetEntity.getRoomId())) {
                AssetStorageRoomEntity roomEntity = roomMapper.selectById(assetEntity.getRoomId());
                // 拼接房间位置和房间名称
                map.put(assetEntity.getRoomId(), roomEntity.getLocationName() + roomEntity.getRoomName());
            }
        });
        // 将AssetEntity转换为AssetVO，并设置房间名称
        List<AssetVO> assetVOS = records.stream().map(assetEntity -> {
            AssetVO assetVO = new AssetVO();
            BeanUtils.copyProperties(assetEntity, assetVO); // 复制属性
            assetVO.setRoomName(map.get(assetEntity.getRoomId())); // 设置房间名称
            return assetVO;
        }).toList();
        // 返回自定义分页DTO，包含资产信息和总记录数
        return new PageCustomDTO<>(assetVOS, page.getTotal());
    }
}
