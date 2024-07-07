package com.ach.domain.system.role;


import com.ach.domain.Repository;

public interface RoleRepository extends Repository<RoleModel> {
    Boolean checkRoleNameIsUnique(String roleName);

    Boolean checkRoleKeyIsUnique(String roleKey);

    Boolean checkRoleNameIsUnique(String roleName, Long excludeRoleId);

    Boolean checkRoleKeyIsUnique(String roleKey, Long excludeRoleId);

}
