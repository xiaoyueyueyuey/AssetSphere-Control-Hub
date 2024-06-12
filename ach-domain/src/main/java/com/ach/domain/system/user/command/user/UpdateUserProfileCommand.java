package com.ach.domain.system.user.command.user;


/**
 *
 */

import com.ach.domain.Command;
import lombok.Data;

/**
 * 修改用户正常信息
 */
@Data
public class UpdateUserProfileCommand implements Command {
    private Long userId;
    private Integer sex;
    private String username;
    private String nickName;
    private String phoneNumber;
    private String email;
}
