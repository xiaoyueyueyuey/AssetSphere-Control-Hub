package com.ach.domain.asset.asset.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteAssetCommand implements Command {

    @Positive
    private Long assetId;

}
