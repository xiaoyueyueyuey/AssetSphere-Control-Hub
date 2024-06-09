package com.ach.domain.system.user.command.user;


import com.ach.domain.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class UpdateUserAvatarCommand implements Command {
    private Long userId;
    private String avatar;
    public UpdateUserAvatarCommand(Long userId, String avatar) {
        this.userId = userId;
        this.avatar = avatar;
    }

}
