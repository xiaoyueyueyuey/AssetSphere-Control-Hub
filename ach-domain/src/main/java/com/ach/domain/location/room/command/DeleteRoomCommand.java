package com.ach.domain.location.room.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DeleteRoomCommand implements Command {

    @NotNull
    @PositiveOrZero
    private Integer roomId;

}
