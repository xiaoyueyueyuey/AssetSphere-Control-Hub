package com.ach.asset.controller;

import com.ach.asset.query.ACQuery;
import com.ach.asset.service.IAssetClassificationService;
import com.ach.asset.vo.ACIdAndNameVO;
import com.ach.asset.vo.ACVO;
import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.asset.classification.command.AddACCommand;
import com.ach.domain.asset.classification.command.ChangeACStatusCommand;
import com.ach.domain.asset.classification.command.DeleteACCommand;
import com.ach.domain.asset.classification.command.UpdateACCommand;
import com.ach.domain.asset.classification.handler.AddACCommandHandler;
import com.ach.domain.asset.classification.handler.ChangeACStatusCommandHandler;
import com.ach.domain.asset.classification.handler.DeleteACCommandHandler;
import com.ach.domain.asset.classification.handler.UpdateACCommandHandler;
import com.ach.infrastructure.page.PageCustomDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "资产分类管理", description = "资产分类管理")
@RequestMapping("/asset/ac")
@Validated
@RequiredArgsConstructor
public class AssetClassificationController {
    private final IAssetClassificationService classificationService;
    private final CommandInvoker commandInvoker;
    private final AddACCommandHandler addACCommandHandler;
    private final UpdateACCommandHandler updateACCommandHandler;
    private final ChangeACStatusCommandHandler changeACStatusCommandHandler;
    private final DeleteACCommandHandler deleteACCommandHandler;

    @Operation(summary = "获取资产分类分页")
    @GetMapping
    public BaseResponseData<PageCustomDTO<ACVO>> getACNav(ACQuery query) {
        PageCustomDTO<ACVO> page = classificationService.getACNav(query);
        return BaseResponseData.ok(page);
    }

    @Operation(summary = "删除资产分类")
    @DeleteMapping("/{acId}")
    public BaseResponseData<Void> deleteAC(@PathVariable("acId") @Positive Integer acId) {
        DeleteACCommand deleteACCommand = new DeleteACCommand();
        deleteACCommand.setAcId(acId);
        commandInvoker.execute(deleteACCommandHandler, deleteACCommand);
        return BaseResponseData.ok();
    }

    @Operation(summary = "新增资产分类")
    @PostMapping
    public BaseResponseData<Void> addAC(@RequestBody AddACCommand command) {
        commandInvoker.execute(addACCommandHandler, command);
        return BaseResponseData.ok();
    }

    @Operation(summary = "更新资产分类")
    @PutMapping
    public BaseResponseData<Void> updateAC(@RequestBody UpdateACCommand command) {
        commandInvoker.execute(updateACCommandHandler, command);
        return BaseResponseData.ok();
    }

    @Operation(summary = "修改资产分类状态(取反)")
    @PutMapping("/status/{acId}")
    public BaseResponseData<Void> changeACStatus(@PathVariable("acId") @Positive Integer acId) {
        ChangeACStatusCommand changeACStatusCommand = new ChangeACStatusCommand();
        changeACStatusCommand.setAcId(acId);
        commandInvoker.execute(changeACStatusCommandHandler, changeACStatusCommand);
        return BaseResponseData.ok();
    }

    @GetMapping("list")
    public BaseResponseData<List<ACIdAndNameVO>> getACList() {
        List<ACIdAndNameVO> acVOList = classificationService.getACList();
        return BaseResponseData.ok(acVOList);
    }
}
