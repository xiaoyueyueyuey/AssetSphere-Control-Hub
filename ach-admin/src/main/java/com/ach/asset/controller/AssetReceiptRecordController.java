package com.ach.asset.controller;

import com.ach.asset.query.ARQuery;
import com.ach.asset.service.IAssetReceiptRecordService;
import com.ach.asset.vo.ARVO;
import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.asset.receipt.AddARCommand;
import com.ach.domain.asset.receipt.AddARCommandHandler;
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
@RequestMapping("/asset/ar")
@RequiredArgsConstructor
public class AssetReceiptRecordController {
    private final IAssetReceiptRecordService arService;
    private final CommandInvoker commandInvoker;
    private final AddARCommandHandler addARCommandHandler;

    @GetMapping()
    public BaseResponseData<PageCustomDTO<ARVO>> getARNav(ARQuery query) {
        return BaseResponseData.ok(arService.getARNav(query));
    }
    @PostMapping()
    public BaseResponseData<Void> addAR(@RequestBody AddARCommand arCommand) {
        commandInvoker.execute(addARCommandHandler, arCommand);
        return BaseResponseData.ok();
    }


}
