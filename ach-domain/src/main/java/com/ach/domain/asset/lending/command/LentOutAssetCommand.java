package com.ach.domain.asset.lending.command;

import com.ach.domain.Command;
import lombok.Data;

@Data
public class LentOutAssetCommand implements Command {
    private Long lendingId;
}
