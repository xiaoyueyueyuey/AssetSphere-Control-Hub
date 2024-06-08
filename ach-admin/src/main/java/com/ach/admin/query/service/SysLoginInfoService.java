package com.ach.admin.query.service;


import com.ach.admin.dto.log.LoginLogDTO;
import com.ach.admin.entity.SysLoginInfoEntity;
import com.ach.admin.query.LoginLogQuery;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-06
 */
public interface SysLoginInfoService extends IService<SysLoginInfoEntity> {

    PageCustomDTO<LoginLogDTO> getLoginInfoList(LoginLogQuery query);
}
