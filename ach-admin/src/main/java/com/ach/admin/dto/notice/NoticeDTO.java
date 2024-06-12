package com.ach.admin.dto.notice;


import com.ach.admin.common.cache.CacheCenter;
import com.ach.admin.entity.SysNoticeEntity;
import com.ach.admin.entity.SysUserEntity;
import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class NoticeDTO {

    private String noticeId;
    private String noticeTitle;
    private Integer noticeType;
    private String noticeContent;
    private Integer status;
    private Date createTime;
    private String creatorName;

    public NoticeDTO(SysNoticeEntity entity) {
        if (entity != null) {
            this.noticeId = entity.getNoticeId() + "";
            this.noticeTitle = entity.getNoticeTitle();
            this.noticeType = entity.getNoticeType();
            this.noticeContent = entity.getNoticeContent();
            this.status = entity.getStatus();
            this.createTime = entity.getCreateTime();

            SysUserEntity cacheUser = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (cacheUser != null) {
                this.creatorName = cacheUser.getUsername();
            }
        }
    }

}
