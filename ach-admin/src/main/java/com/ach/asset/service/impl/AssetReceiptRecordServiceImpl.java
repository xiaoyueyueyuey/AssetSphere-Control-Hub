package com.ach.asset.service.impl;

import com.ach.admin.mapper.SysUserMapper;
import com.ach.asset.entity.AssetReceiptRecordEntity;
import com.ach.asset.mapper.AssetClassificationMapper;
import com.ach.asset.mapper.AssetReceiptRecordMapper;
import com.ach.asset.query.ARQuery;
import com.ach.asset.service.IAssetReceiptRecordService;
import com.ach.asset.vo.ARVO;
import com.ach.infrastructure.page.PageCustomDTO;
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
public class AssetReceiptRecordServiceImpl extends ServiceImpl<AssetReceiptRecordMapper, AssetReceiptRecordEntity> implements IAssetReceiptRecordService {
    private final AssetStorageRoomMapper roomMapper;
    private final SysUserMapper userMapper;
    private final AssetClassificationMapper acMapper;

    @Override
    public PageCustomDTO<ARVO> getARNav(ARQuery query) {
        Page<AssetReceiptRecordEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<AssetReceiptRecordEntity> records = page.getRecords();
        Map<Integer, String> userMap = new HashMap<>();
        Map<Integer, String> roomMap = new HashMap<>();
        Map<Integer, String> assetTypeMap = new HashMap<>();
        //TODO 获取入库人，房间名称，资产类别
        records.forEach(record -> {
            Integer roomId = record.getRoomId();
            if (!roomMap.containsKey(roomId)) {
                roomMap.put(roomId, roomMapper.selectById(roomId).getRoomName());
            }
            Long userId = record.getCreatorId();
            if (!userMap.containsKey(userId)) {
                userMap.put(userId.intValue(), userMapper.selectById(userId).getUsername());
            }
            Integer acId = record.getAcId();
            if (!assetTypeMap.containsKey(acId)) {
                assetTypeMap.put(acId, acMapper.selectById(acId).getAcName());
            }
        });

        // 映射 入库人，房间名称，资产类别
        List<ARVO> list = page.getRecords().stream().map(
                record -> {
                    ARVO vo = new ARVO();
                    BeanUtils.copyProperties(record, vo);
                    vo.setRoomName(roomMap.get(record.getRoomId()));
                    vo.setCreator(userMap.get(record.getCreatorId().intValue()));
                    vo.setAcName(assetTypeMap.get(record.getAcId()));
                    return vo;
                }
        ).toList();

        return new PageCustomDTO<>(list, page.getTotal());


    }
}
