package com.ach.admin.dto.user;


import com.ach.admin.dto.role.RoleDTO;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserInfoDTO {
    private UserDTO user;
    private RoleDTO role;
}
