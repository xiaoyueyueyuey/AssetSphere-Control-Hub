package com.ach.admin.common.dto.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
public class UploadFileDTO {

    private String imgUrl;

    public UploadFileDTO(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
