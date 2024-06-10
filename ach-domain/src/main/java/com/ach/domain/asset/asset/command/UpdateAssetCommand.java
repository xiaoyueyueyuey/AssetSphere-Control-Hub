package com.ach.domain.asset.asset.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateAssetCommand implements Command {
    @Positive
    private Long assetId;
    @Positive
    private Integer acId;
    @NotBlank
    private String assetName;
    @NotBlank
    private String assetModel;
    @Positive
    private Integer roomId;
    private String remark;
}
