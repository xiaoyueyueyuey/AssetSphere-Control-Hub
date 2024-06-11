package com.ach.audit.controller;

import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.audit.asset.lending.AuditAssetLendingCommand;
import com.ach.domain.audit.asset.lending.AuditAssetLendingCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xy
 * @since 2024-06-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/audit/alAudit")
public class AssetLendingAuditController {
    private final CommandInvoker commandInvoker;
    private final AuditAssetLendingCommandHandler handler;

    @PutMapping
    public BaseResponseData<Void> auditAssetLending(@RequestBody AuditAssetLendingCommand command) {
        commandInvoker.execute(handler, command);
        return BaseResponseData.ok();
    }

}
