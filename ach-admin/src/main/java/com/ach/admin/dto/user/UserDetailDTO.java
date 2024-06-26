package com.ach.admin.dto.user;


import com.ach.admin.dto.post.PostDTO;
import com.ach.admin.dto.role.RoleDTO;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 
 */
@Data
public class UserDetailDTO {
    private UserDTO user;
    /**
     * 返回所有role
     */
    private List<RoleDTO> roleOptions;//角色集合
    /**
     * 返回所有posts
     */
    private List<PostDTO> postOptions;

    private Long postId;//岗位id
    private Long roleId;//角色id

    private Set<String> permissions;//权限集合

}
