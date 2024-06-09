package com.ach.domain.system.user.command.manager;


import com.ach.domain.Command;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserLoginIpAndTimeCommand implements Command {
    private Long userId;
    private String loginIp;
    private Date loginDate;
}
