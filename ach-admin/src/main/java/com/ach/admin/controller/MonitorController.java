package com.ach.admin.controller;


import com.ach.admin.common.cache.CacheCenter;
import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.monitor.OnlineUserDTO;
import com.ach.admin.dto.monitor.RedisCacheInfoDTO;
import com.ach.admin.dto.monitor.SearchDTO;
import com.ach.admin.dto.monitor.ServerInfo;
import com.ach.admin.query.service.impl.MonitorServiceImpl;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.page.PageCustomDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 缓存监控
 *
 * @author valarchie
 */
@Tag(name = "监控API", description = "监控相关信息")
@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorController extends BaseController {

    private final MonitorServiceImpl monitorServiceImpl;

    @Operation(summary = "Redis信息")
    @PreAuthorize("@permission.has('monitor:cache:list')")
    @GetMapping("/cacheInfo")
    public BaseResponseData<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorServiceImpl.getRedisCacheInfo();
        return BaseResponseData.ok(redisCacheInfo);
    }

    @Operation(summary = "服务器信息")
    @PreAuthorize("@permission.has('monitor:server:list')")
    @GetMapping("/serverInfo")
    public BaseResponseData<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorServiceImpl.getServerInfo();
        return BaseResponseData.ok(serverInfo);
    }

    /**
     * 获取在线用户列表
     *
     * @param ipAddress ip地址
     * @param username  用户名
     * @return 分页处理后的在线用户信息
     */
    @Operation(summary = "在线用户列表")
    @PreAuthorize("@permission.has('monitor:online:list')")
    @GetMapping("/onlineUsers")
    public BaseResponseData<PageCustomDTO<OnlineUserDTO>> onlineUsers(@RequestBody(required = false) SearchDTO searchDTO) {
        if (searchDTO == null) {
            searchDTO = new SearchDTO();

        }
        List<OnlineUserDTO> onlineUserList = monitorServiceImpl.getOnlineUserList(searchDTO.getUsername(), searchDTO.getIpAddress());
        return BaseResponseData.ok(new PageCustomDTO<>(onlineUserList));
    }

    /**
     * 强退用户
     */
    @Operation(summary = "强退用户")
    @PreAuthorize("@permission.has('monitor:online:forceLogout')")
    @AccessLog(title = "在线用户", businessType = BusinessTypeEnum.FORCE_LOGOUT)
    @DeleteMapping("/onlineUser/{tokenId}")
    public BaseResponseData<Void> logoutOnlineUser(@PathVariable String tokenId) {
        CacheCenter.loginUserCache.delete(tokenId);
        return BaseResponseData.ok();
    }


}
