package com.ach.location.controller;

import com.ach.common.base.BaseResponseData;
import com.ach.location.service.IAssetStorageLocationService;
import com.ach.location.vo.LocationVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@Tag(name = "教学楼API", description = "教学楼相关接口")
public class AssetStorageLocationController {
    private final IAssetStorageLocationService locationService;
    //TODO 上权限校验
    @Operation(summary = "获取教学楼列表")
    @GetMapping
    public BaseResponseData<List<LocationVO>> getLocationList() {
        List<LocationVO> locationVOList=  locationService.getLocationList();
        return BaseResponseData.ok(locationVOList);
    }



}
