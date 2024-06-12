package com.ach.admin.controller;


import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.notice.NoticeDTO;
import com.ach.admin.entity.SysNoticeEntity;
import com.ach.admin.query.NoticeQuery;
import com.ach.admin.service.SysNoticeService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.notice.command.AddNoticeCommand;
import com.ach.domain.system.notice.command.DeleteNoticeCommand;
import com.ach.domain.system.notice.command.UpdateNoticeCommand;
import com.ach.domain.system.notice.handler.NoticeAddCommandHandler;
import com.ach.domain.system.notice.handler.NoticeDeleteCommandHandler;
import com.ach.domain.system.notice.handler.NoticeUpdateCommandHandler;
import com.ach.infrastructure.annotations.unrepeatable.Unrepeatable1;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公告 信息操作处理
 *
 * 
 */
@Tag(name = "公告API", description = "公告相关的增删查改")
@RestController
@RequestMapping("/system/notices")
@Validated
@RequiredArgsConstructor
public class SysNoticeController extends BaseController {

    @Resource
    private SysNoticeService sysNoticeService;
    @Resource
    private NoticeAddCommandHandler noticeAddCommandHandler;
    @Resource
    private NoticeUpdateCommandHandler noticeUpdateCommandHandler;
    @Resource
    private NoticeDeleteCommandHandler noticeDeleteCommandHandler;
    @Resource
    private CommandInvoker commandInvoker;

    /**
     * 获取通知公告列表
     */
    @Operation(summary = "公告列表")
    @PreAuthorize("@permission.has('system:notice:list')")
    @GetMapping
    public BaseResponseData<PageCustomDTO<NoticeDTO>> list(NoticeQuery query) {
        Page<SysNoticeEntity> page = sysNoticeService.getNoticeList(query.toPage(), query.toQueryWrapper());
        List<NoticeDTO> records = page.getRecords().stream().map(NoticeDTO::new).collect(Collectors.toList());
        PageCustomDTO<NoticeDTO> noticeDTOPageCustomDTO = new PageCustomDTO<>(records, page.getTotal());
        return BaseResponseData.ok(noticeDTOPageCustomDTO);
    }

    /**
     * 获取通知公告列表
     * 从从库获取数据 例子 仅供参考
     */
    @Operation(summary = "公告列表（从数据库从库获取）", description = "演示主从库的例子")
    @DS("slave")
    @PreAuthorize("@permission.has('system:notice:list')")
    @GetMapping("/database/slave")
    public BaseResponseData<PageCustomDTO<NoticeDTO>> listFromSlave(NoticeQuery query) {
        Page<SysNoticeEntity> page = sysNoticeService.getNoticeList(query.toPage(), query.toQueryWrapper());
        List<NoticeDTO> records = page.getRecords().stream().map(NoticeDTO::new).collect(Collectors.toList());
        PageCustomDTO<NoticeDTO> noticeDTOPageCustomDTO = new PageCustomDTO<>(records, page.getTotal());
        return BaseResponseData.ok(noticeDTOPageCustomDTO);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @Operation(summary = "公告详情")
    @PreAuthorize("@permission.has('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public BaseResponseData<NoticeDTO> getInfo(@PathVariable @NotNull @Positive Long noticeId) {
        return BaseResponseData.ok(sysNoticeService.getNoticeInfo(noticeId));
    }

    /**
     * 新增通知公告
     */
    @Operation(summary = "添加公告")
    @Unrepeatable1(interval = 60, checkType = Unrepeatable1.CheckType.SYSTEM_USER)
    @PreAuthorize("@permission.has('system:notice:add')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public BaseResponseData<Void> add(@RequestBody AddNoticeCommand addCommand) {
        Boolean execute = commandInvoker.execute(noticeAddCommandHandler, addCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 修改通知公告
     */
    @Operation(summary = "修改公告")
    @PreAuthorize("@permission.has('system:notice:edit')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{noticeId}")
    public BaseResponseData<Void> edit(@PathVariable Long noticeId, @RequestBody UpdateNoticeCommand updateCommand) {
        Boolean execute = commandInvoker.execute(noticeUpdateCommandHandler, updateCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 删除通知公告
     */
    @Operation(summary = "删除公告")
    @PreAuthorize("@permission.has('system:notice:remove')")
    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{noticeId}")
    public BaseResponseData<Void> remove(@PathVariable("noticeId") Long noticeId) {
        DeleteNoticeCommand deleteNoticeCommand = new DeleteNoticeCommand();
        deleteNoticeCommand.setNoticeId(noticeId);
        Boolean execute = commandInvoker.execute(noticeDeleteCommandHandler, deleteNoticeCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }
    //TODO 批量删除
//        commandInvoker.execute(noticeDeleteCommandHandler,new BulkOperationCommand<Integer>(noticeIds));
//    @Operation(summary = "删除公告")
//    @PreAuthorize("@permission.has('system:notice:remove')")
//    @AccessLog(title = "通知公告", businessType = BusinessTypeEnum.DELETE)
//    @DeleteMapping
//    public BaseResponseData<Void> remove(@RequestParam List<Integer> noticeIds) {
//        commandInvoker.execute(noticeDeleteCommandHandler,new BulkOperationCommand<Integer>(noticeIds));
//        return BaseResponseData.ok();
//    }

}
