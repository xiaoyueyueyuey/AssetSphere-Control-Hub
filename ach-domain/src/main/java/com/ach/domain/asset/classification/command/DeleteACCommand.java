package com.ach.domain.asset.classification.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteACCommand implements Command {

    @Positive
    private Integer acId;

}
