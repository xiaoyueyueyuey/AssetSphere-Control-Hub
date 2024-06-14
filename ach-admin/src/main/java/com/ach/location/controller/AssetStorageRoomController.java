package com.ach.location.controller;

import com.ach.common.base.BaseResponseData;
import com.ach.domain.CommandInvoker;
import com.ach.domain.location.room.command.AddRoomCommand;
import com.ach.domain.location.room.command.ChangeRoomStatusCommand;
import com.ach.domain.location.room.command.DeleteRoomCommand;
import com.ach.domain.location.room.command.UpdateRoomCommand;
import com.ach.domain.location.room.handler.AddRoomCommandHandler;
import com.ach.domain.location.room.handler.ChangeRoomStatusCommandHandler;
import com.ach.domain.location.room.handler.DeleteRoomCommandHandler;
import com.ach.domain.location.room.handler.UpdateRoomCommandHandler;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.location.query.RoomQuery;
import com.ach.location.service.impl.IAssetStorageRoomService;
import com.ach.location.vo.RoomVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Tag(name = "存储室管理", description = "存储室管理")
@Validated
public class AssetStorageRoomController {
    private final IAssetStorageRoomService roomService;

    private final CommandInvoker commandInvoker;
    private final AddRoomCommandHandler addRoomCommandHandler;
    private final UpdateRoomCommandHandler updateRoomCommandHandler;
    private final ChangeRoomStatusCommandHandler changeRoomStatusCommandHandler;
    private final DeleteRoomCommandHandler deleteRoomCommandHandler;

    @GetMapping
    public BaseResponseData<PageCustomDTO<RoomVO>> getRoomNav(RoomQuery query) {
        PageCustomDTO<RoomVO> page = roomService.getRoomNav(query);
        return BaseResponseData.ok(page);
    }

    @GetMapping("/tree")
    public BaseResponseData<List<RoomVO>> getLocationAndRoomTree() {
        List<RoomVO> locationAndRoomTree = roomService.getLocationAndRoomTree();
        return BaseResponseData.ok(locationAndRoomTree);
    }

    @DeleteMapping("/{roomId}")
    public BaseResponseData<Void> deleteRoom(@PathVariable("roomId") @Positive Integer roomId) {
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand();
        deleteRoomCommand.setRoomId(roomId);
        commandInvoker.execute(deleteRoomCommandHandler, deleteRoomCommand);
        return BaseResponseData.ok();
    }

    @PostMapping()
    public BaseResponseData<Void> addRoom(@RequestBody AddRoomCommand command) {
        commandInvoker.execute(addRoomCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping()
    public BaseResponseData<Void> updateRoom(@RequestBody UpdateRoomCommand command) {
        commandInvoker.execute(updateRoomCommandHandler, command);
        return BaseResponseData.ok();
    }

    @PutMapping("/status/{roomId}")
    public BaseResponseData<Void> changeRoomStatus(@PathVariable("roomId") @Positive Integer roomId) {
        ChangeRoomStatusCommand changeRoomStatusCommand = new ChangeRoomStatusCommand();
        changeRoomStatusCommand.setRoomId(roomId);
        commandInvoker.execute(changeRoomStatusCommandHandler, changeRoomStatusCommand);
        return BaseResponseData.ok();
    }


}
