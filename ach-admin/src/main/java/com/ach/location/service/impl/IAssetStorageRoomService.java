package com.ach.location.service.impl;


import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.location.entity.AssetStorageRoomEntity;
import com.ach.location.query.RoomQuery;
import com.ach.location.vo.RoomVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
public interface IAssetStorageRoomService extends IService<AssetStorageRoomEntity> {
    PageCustomDTO<RoomVO> getRoomNav(RoomQuery query);

    List<RoomVO> getLocationAndRoomTree();
}
