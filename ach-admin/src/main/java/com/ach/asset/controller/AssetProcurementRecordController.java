package com.ach.asset.controller;

import com.ach.asset.query.APQuery;
import com.ach.asset.service.IAssetProcurementRecordService;
import com.ach.asset.vo.ARVO;
import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.asset.procurement.command.ApplyForProcureAssetCommand;
import com.ach.domain.asset.procurement.command.CancelProcureAssetCommand;
import com.ach.domain.asset.procurement.command.ChangeAssetProcurementStatusCommand;
import com.ach.domain.asset.procurement.handler.ApplyForProcureAssetCommandHandler;
import com.ach.domain.asset.procurement.handler.CancelProcureAssetCommandHandler;
import com.ach.domain.asset.procurement.handler.ChangeAssetProcurementStatusCommandHandler;
import com.ach.infrastructure.page.PageCustomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */

@Validated
@RestController
@RequestMapping("/asset/ap")

@RequiredArgsConstructor
public class AssetProcurementRecordController {

    public final CommandInvoker commandInvoker;
    public final IAssetProcurementRecordService service;
    public final ChangeAssetProcurementStatusCommandHandler changeAssetProcurementStatusCommandHandler;
    public final ApplyForProcureAssetCommandHandler applyForProcureAssetCommandHandler;
    public final CancelProcureAssetCommandHandler cancelProcureAssetCommandHandler;

    @GetMapping
    public BaseResponseData<PageCustomDTO<ARVO>> getAPNav(APQuery query) {
        System.out.println("APQuery" + query);
        return BaseResponseData.ok(service.getAPNav(query));
    }


    @PostMapping
    public BaseResponseData<Void> applyForProcureAsset(@RequestBody ApplyForProcureAssetCommand command) {
        commandInvoker.execute(applyForProcureAssetCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping("/{id}")
    public BaseResponseData<Void> cancelProcureAsset(@PathVariable("id") Long id) {
        CancelProcureAssetCommand cancelProcureAssetCommand = new CancelProcureAssetCommand();
        cancelProcureAssetCommand.setProcurementId(id);
        commandInvoker.execute(cancelProcureAssetCommandHandler, cancelProcureAssetCommand);
        return BaseResponseData.ok();
    }

    @PutMapping("/status")
    public BaseResponseData<Void> changeAssetProcurementStatus(@RequestBody ChangeAssetProcurementStatusCommand command) {
        commandInvoker.execute(changeAssetProcurementStatusCommandHandler, command);
        return BaseResponseData.ok();
    }


}
