package com.ach.domain.system.log.login.command;


import com.ach.domain.Command;
import lombok.Data;

import java.util.List;

@Data
public class DeleteLoginLogCommand implements Command {
    private List<Long> infoIds;
}
