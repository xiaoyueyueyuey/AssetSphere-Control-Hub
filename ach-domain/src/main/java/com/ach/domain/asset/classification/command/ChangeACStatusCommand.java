package com.ach.domain.asset.classification.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ChangeACStatusCommand implements Command {
    @PositiveOrZero
    private Integer acId;

}
