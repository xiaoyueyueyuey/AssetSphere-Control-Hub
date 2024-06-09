package com.ach.domain.system.menu.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeleteMenuCommand implements Command {
    @NotNull
    private Long menuId;
}
