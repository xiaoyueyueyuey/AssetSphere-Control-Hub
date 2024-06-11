package com.ach.audit.controller;

import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.audit.asset.procurement.AuditAssetProcurementCommand;
import com.ach.domain.audit.asset.procurement.AuditAssetProcurementCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/audit/assetProcurementAudit")
public class AssetProcurementAuditController {
    private final CommandInvoker commandInvoker;
    private final AuditAssetProcurementCommandHandler handler;

    @PutMapping
    public BaseResponseData<Void> auditAssetProcurement(@RequestBody AuditAssetProcurementCommand command) {
        commandInvoker.execute(handler, command);
        return BaseResponseData.ok();
    }

}
