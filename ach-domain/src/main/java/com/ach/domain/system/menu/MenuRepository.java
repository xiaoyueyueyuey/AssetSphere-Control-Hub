package com.ach.domain.system.menu;


import com.ach.domain.Repository;

public interface MenuRepository extends Repository<MenuModel> {
    Boolean findByMenuNameOrError(String menuName);

    Boolean findByMenuNameOrError(String menuName, Long menuId);

}
