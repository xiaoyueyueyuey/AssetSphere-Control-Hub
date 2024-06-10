package com.ach.domain.asset.classification.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateACCommand implements Command {
    @Positive
    private Integer acId;
    @NotBlank(message = "资产分类名称不能为空")
    @Size(max = 30, message = "资产名称长度不能超过30个字符")
    private String acName;
    @Positive
    @NotNull
    private String remark;
    @PositiveOrZero
    private Integer sort;
}
