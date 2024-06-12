package com.ach.admin.service.impl;


import com.ach.admin.dto.notice.NoticeDTO;
import com.ach.admin.entity.SysNoticeEntity;
import com.ach.admin.mapper.SysNoticeMapper;
import com.ach.admin.service.SysNoticeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 *
 * @since 2022-06-16
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeEntity> implements SysNoticeService {

    /**
     * 获取公告列表
     *
     * @param page         页码对象
     * @param queryWrapper 查询对象
     * @return
     */
    @Override
    public Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page, Wrapper<SysNoticeEntity> queryWrapper) {
        return this.baseMapper.getNoticeList(page, queryWrapper);
    }

    /**
     * 获得公告详情
     *
     * @param noticeId
     * @return
     */

    @Override
    public NoticeDTO getNoticeInfo(Long noticeId) {
        SysNoticeEntity sysNoticeEntity = this.baseMapper.selectById(noticeId);
        return new NoticeDTO(sysNoticeEntity);

    }

}
