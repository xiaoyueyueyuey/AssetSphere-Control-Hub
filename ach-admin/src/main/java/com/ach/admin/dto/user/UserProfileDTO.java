package com.ach.admin.dto.user;


import com.ach.admin.entity.SysPostEntity;
import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.entity.SysUserEntity;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserProfileDTO {
    private UserDTO user;
    private String roleName;
    private String postName;
    public UserProfileDTO(SysUserEntity userEntity, SysPostEntity postEntity, SysRoleEntity roleEntity) {
        if (userEntity != null) {
            this.user = new UserDTO(userEntity);
        }

        if (postEntity != null) {
            this.postName = postEntity.getPostName();
        }

        if (roleEntity != null) {
            this.roleName = roleEntity.getRoleName();
        }
    }

}
