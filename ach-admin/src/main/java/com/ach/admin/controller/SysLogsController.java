package com.ach.admin.controller;


import cn.hutool.core.collection.CollUtil;
import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.log.LoginLogDTO;
import com.ach.admin.dto.log.OperationLogDTO;
import com.ach.admin.query.LoginLogQuery;
import com.ach.admin.query.OperationLogQuery;
import com.ach.admin.service.SysLoginInfoService;
import com.ach.admin.service.SysOperationLogService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.log.login.command.DeleteLoginLogCommand;
import com.ach.domain.system.log.login.handler.DeleteLoginInfoHandler;
import com.ach.domain.system.log.operation.command.DeleteOperationLogCommand;
import com.ach.domain.system.log.operation.handler.DeleteOperationLogHandler;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.infrastructure.utils.poi.CustomExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统访问记录
 *
 * 
 */
@Tag(name = "日志API", description = "日志相关API")
@RestController
@RequestMapping("/logs")
@Validated
@RequiredArgsConstructor
public class SysLogsController extends BaseController {
    @Resource
    SysLoginInfoService sysLoginInfoService;
    @Resource
    SysOperationLogService sysOperationLogService;
    @Resource
    CommandInvoker commandInvoker;
    @Resource
    DeleteLoginInfoHandler deleteLoginInfoHandler;
    @Resource
    DeleteOperationLogHandler deleteOperationLogHandler;

    @Operation(summary = "登录日志列表")
    @PreAuthorize("@permission.has('monitor:logininfor:list')")
    @GetMapping("/loginLogs")
    public BaseResponseData<PageCustomDTO<LoginLogDTO>> loginInfoList(LoginLogQuery query) {
        PageCustomDTO<LoginLogDTO> pageCustomDTO = sysLoginInfoService.getLoginInfoList(query);
        return BaseResponseData.ok(pageCustomDTO);
    }

    @Operation(summary = "登录日志导出", description = "将登录日志导出到excel")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:logininfor:export')")
    @GetMapping("/loginLogs/excel")
    public void loginInfosExcel(HttpServletResponse response, LoginLogQuery query) {
        PageCustomDTO<LoginLogDTO> pageCustomDTO = sysLoginInfoService.getLoginInfoList(query);
        CustomExcelUtil.writeToResponse(pageCustomDTO.getRows(), LoginLogDTO.class, response);
    }

    @Operation(summary = "删除登录日志")
    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/loginLogs")
    public BaseResponseData<Void> removeLoginInfos(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        ArrayList<Long> distinct = CollUtil.distinct(ids);
        DeleteLoginLogCommand deleteLoginLogCommand = new DeleteLoginLogCommand();
        deleteLoginLogCommand.setInfoIds(distinct);
        Boolean execute = commandInvoker.execute(deleteLoginInfoHandler, deleteLoginLogCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    @Operation(summary = "操作日志列表")
    @PreAuthorize("@permission.has('monitor:operlog:list')")
    @GetMapping("/operationLogs")
    public BaseResponseData<PageCustomDTO<OperationLogDTO>> operationLogs(OperationLogQuery query) {
        PageCustomDTO<OperationLogDTO> pageCustomDTO = sysOperationLogService.getOperationLogList(query);
        return BaseResponseData.ok(pageCustomDTO);
    }
//    @GetMapping("/download")
//    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
//        // 从文件系统或其他位置获取文件输入流
//        File file = new File("path/to/file");
//        InputStream inputStream = new FileInputStream(file);
//        CustomExcelUtil.wri
//
//        // 创建一个 InputStreamResource 对象，将文件输入流包装在其中
//        InputStreamResource resource = new InputStreamResource(inputStream);
//
//        // 返回 ResponseEntity 对象，其中包含 InputStreamResource 对象和文件名
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(file.length())
//                .body(resource);
//    }

    /**
     * 可否改成以上的形式 TODO
     *
     * @param response
     * @param query
     */
    @Operation(summary = "操作日志导出")
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:operlog:export')")
    @GetMapping("/operationLogs/excel")
    public void operationLogsExcel(HttpServletResponse response, OperationLogQuery query) {
        PageCustomDTO<OperationLogDTO> pageCustomDTO = sysOperationLogService.getOperationLogList(query);
        CustomExcelUtil.writeToResponse(pageCustomDTO.getRows(), OperationLogDTO.class, response);
    }

    @Operation(summary = "删除操作日志")
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("@permission.has('monitor:operlog:remove')")
    @DeleteMapping("/operationLogs")
    public BaseResponseData<Void> removeOperationLogs(@RequestParam List<Long> operationIds) {
        List ids = CollUtil.distinct(operationIds);//去重
        DeleteOperationLogCommand deleteOperationLogCommand = new DeleteOperationLogCommand();
        deleteOperationLogCommand.setOperationIds(ids);
        commandInvoker.execute(deleteOperationLogHandler, deleteOperationLogCommand);
        return BaseResponseData.ok();
    }
}
