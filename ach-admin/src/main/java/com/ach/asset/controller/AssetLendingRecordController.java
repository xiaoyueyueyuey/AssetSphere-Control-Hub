package com.ach.asset.controller;

import com.ach.asset.query.ALQuery;
import com.ach.asset.service.IAssetLendingRecordService;
import com.ach.asset.vo.ALVO;
import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.asset.lending.command.ApplyForLendingAssetCommand;
import com.ach.domain.asset.lending.command.CancelAssetLendingCommand;
import com.ach.domain.asset.lending.command.LentOutAssetCommand;
import com.ach.domain.asset.lending.command.ReturnAssetCommand;
import com.ach.domain.asset.lending.handler.ApplyForLendingAssetCommandHandler;
import com.ach.domain.asset.lending.handler.CancelAssetLendingCommandHandler;
import com.ach.domain.asset.lending.handler.LentOutAssetCommandHandler;
import com.ach.domain.asset.lending.handler.ReturnAssetCommandHandler;
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
@RestController
@RequestMapping("/asset/al")
@Validated
@RequiredArgsConstructor
public class AssetLendingRecordController {

    private final CommandInvoker commandInvoker;
    private final ApplyForLendingAssetCommandHandler applyForLendingAssetCommandHandler;
    private final CancelAssetLendingCommandHandler cancelAssetLendingCommandHandler;
    private final ReturnAssetCommandHandler returnAssetCommandHandler;
    private final IAssetLendingRecordService assetLendingRecordService;
    private final LentOutAssetCommandHandler lentOutAssetCommandHandler;

    @GetMapping
    public BaseResponseData<PageCustomDTO<ALVO>> getALNav(ALQuery query) {
        System.out.println("query = " + query);
        return BaseResponseData.ok(assetLendingRecordService.getALNav(query));
    }

    @GetMapping("self")
    public BaseResponseData<PageCustomDTO<ALVO>> getSelfALNav(ALQuery query) {
        return BaseResponseData.ok(assetLendingRecordService.getSelfALNav(query));
    }

    @PostMapping()
    public BaseResponseData<Void> applyForLendingAsset(@RequestBody ApplyForLendingAssetCommand command) {
        commandInvoker.execute(applyForLendingAssetCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping("/cancel/{lendingId}")
    public BaseResponseData<Void> cancelAssetLending(@PathVariable("lendingId") Long lendingId) {
        CancelAssetLendingCommand command = new CancelAssetLendingCommand();
        command.setLendingId(lendingId);
        commandInvoker.execute(cancelAssetLendingCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping("/lending/{lendingId}")
    public BaseResponseData<Void> lendingAsset(@PathVariable("lendingId") Long lendingId) {
        LentOutAssetCommand command = new LentOutAssetCommand();
        command.setLendingId(lendingId);
        commandInvoker.execute(lentOutAssetCommandHandler, command);
        return BaseResponseData.ok();
    }


    @PutMapping("return/{lendingId}")
    public BaseResponseData<Void> returnAsset(@PathVariable("lendingId") Long lendingId) {
        ReturnAssetCommand command = new ReturnAssetCommand();
        command.setLendingId(lendingId);
        commandInvoker.execute(returnAssetCommandHandler, command);
        return BaseResponseData.ok();
    }


}
