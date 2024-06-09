package com.ach.location.service.impl;

import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.mapper.AssetStorageRoomMapper;
import com.ach.location.query.RoomQuery;
import com.ach.location.vo.RoomVO;
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
public class AssetStorageRoomServiceImpl extends ServiceImpl<AssetStorageRoomMapper, AssetStorageRoomEntity> implements IAssetStorageRoomService {

    @Override
    public PageCustomDTO<RoomVO> getRoomNav(RoomQuery query) {
        Page<AssetStorageRoomEntity> page = this.page(query.toPage(), query.toQueryWrapper());
        List<RoomVO> records=page.getRecords().stream().map(RoomVO::new).toList();
        return  new PageCustomDTO<>(records,page.getTotal());
    }
}
