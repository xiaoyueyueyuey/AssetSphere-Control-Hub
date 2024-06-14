package com.ach.asset.controller;

import com.ach.asset.query.AssetQuery;
import com.ach.asset.service.IAssetService;
import com.ach.asset.vo.AssetVO;
import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.asset.asset.command.ChangeAssetStatusCommand;
import com.ach.domain.asset.asset.command.DeleteAssetCommand;
import com.ach.domain.asset.asset.command.UpdateAssetCommand;
import com.ach.domain.asset.asset.handler.ChangeAssetStatusCommandHandler;
import com.ach.domain.asset.asset.handler.DeleteAssetCommandHandler;
import com.ach.domain.asset.asset.handler.UpdateAssetCommandHandler;
import com.ach.infrastructure.page.PageCustomDTO;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**

 *
 * @author xy
 * @since 2024-06-08
 */
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/asset")
public class AssetController {
    private final IAssetService assetService;
    private final CommandInvoker commandInvoker;
    private final UpdateAssetCommandHandler updateAssetCommandHandler;
    private final ChangeAssetStatusCommandHandler changeAssetStatusCommandHandler;
    private final DeleteAssetCommandHandler deleteAssetCommandHandler;
    @GetMapping
    public BaseResponseData<PageCustomDTO<AssetVO>> getAssetNav(AssetQuery query) {
        return BaseResponseData.ok(assetService.getAssetNav(query));
    }

    @DeleteMapping("/{id}")
    public BaseResponseData<Void> deleteAsset(@PathVariable("id") @Positive Long id) {
        DeleteAssetCommand deleteAssetCommand = new DeleteAssetCommand();
        deleteAssetCommand.setAssetId(id);
        commandInvoker.execute(deleteAssetCommandHandler, deleteAssetCommand);
        return BaseResponseData.ok();
    }

    @PutMapping
    public BaseResponseData<Void> updateAsset(@RequestBody UpdateAssetCommand command) {
        commandInvoker.execute(updateAssetCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping("/status")
    public BaseResponseData<Void> changeAssetStatus(@RequestBody ChangeAssetStatusCommand command) {
        commandInvoker.execute(changeAssetStatusCommandHandler, command);
        return BaseResponseData.ok();
    }


}



