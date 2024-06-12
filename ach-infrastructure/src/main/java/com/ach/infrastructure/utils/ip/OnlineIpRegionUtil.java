package com.ach.infrastructure.utils.ip;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.ach.common.utils.jackson.JacksonUtil;
import com.ach.infrastructure.config.XYBootConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * query geography address from ip
 *
 * 
 */
@Slf4j
public class OnlineIpRegionUtil {

    /**
     * website for query geography address from ip
     */
    public static final String ADDRESS_QUERY_SITE = "http://whois.pconline.com.cn/ipJson.jsp";

    private OnlineIpRegionUtil() {
    }

    public static IpRegion getIpRegion(String ip) {
        if (StrUtil.isBlank(ip) || IpUtil.isValidIpv6(ip) || !IpUtil.isValidIpv4(ip)) {
            return null;
        }

        if (XYBootConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtil.get(ADDRESS_QUERY_SITE + "?ip=" + ip + "&json=true",
                        CharsetUtil.CHARSET_GBK);

                if (StrUtil.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return null;
                }

                String province = JacksonUtil.getAsString(rspStr, "pro");
                String city = JacksonUtil.getAsString(rspStr, "city");
                return new IpRegion(province, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return null;
    }

}
