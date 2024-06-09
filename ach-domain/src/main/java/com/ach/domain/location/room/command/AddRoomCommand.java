package com.ach.domain.location.room.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddRoomCommand implements Command {
    @NotBlank(message = "课室名称不能为空")
    @Size(max = 30, message = "课室名称长度不能超过30个字符")
    private String roomName;
    @Positive
    @NotNull
    private Integer locationId;
    private String remark;
    @PositiveOrZero
    private Integer roomSort;
}
