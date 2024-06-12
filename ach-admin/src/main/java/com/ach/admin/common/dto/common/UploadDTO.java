package com.ach.admin.common.dto.common;

import lombok.Builder;
import lombok.Data;

/**
 *
 */
@Data
@Builder
public class UploadDTO {

    private String url;
    private String fileName;
    private String newFileName;
    private String originalFilename;

}
