package com.ach.admin.service;


import com.ach.admin.dto.notice.NoticeDTO;
import com.ach.admin.entity.SysNoticeEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * 
 * @since 2022-06-16
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {

    /**
     * 获取公告列表
     *
     * @param page         页码对象
     * @param queryWrapper 查询对象
     * @return 分页处理后的公告列表
     */
    Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page,
                                        @Param(Constants.WRAPPER) Wrapper<SysNoticeEntity> queryWrapper);

    NoticeDTO getNoticeInfo(Long noticeId);
}
