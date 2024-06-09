package com.ach.domain.system.dept.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DeleteDeptCommand implements Command {
    @NotNull
    @PositiveOrZero
    private Long deptId;
}
