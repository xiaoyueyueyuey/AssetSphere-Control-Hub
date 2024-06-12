package com.ach.infrastructure.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 分页模型类
 *
 * 
 */
@Data
public class PageCustomDTO<T> {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<T> rows;

    public PageCustomDTO(List<T> list) {
        this.rows = list;
        this.total = (long) list.size();
    }

    public PageCustomDTO(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

    public PageCustomDTO(List<T> list, Long count) {
        this.rows = list;
        this.total = count;
    }

}
