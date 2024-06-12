package com.ach.admin.dto.login;


import com.ach.common.enums.dictionary.DictionaryData;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 
 */
@Data
public class ConfigDTO {
    //是否使用验证码
    private Boolean isCaptchaOn;
    private Map<String, List<DictionaryData>> dictionary;
}
