package com.ach.admin.common.dto.common;

import com.ach.admin.dto.user.UserDTO;
import lombok.Data;

import java.util.Set;

/**
 * 
 */
@Data
public class CurrentLoginUserDTO {

    private UserDTO userInfo;
    private String roleKey;
    private Set<String> permissions;


}
