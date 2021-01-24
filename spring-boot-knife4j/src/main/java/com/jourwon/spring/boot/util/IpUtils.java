package com.jourwon.spring.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP地址工具类
 *
 * @author JourWon
 * @date 2021/1/22
 */
@Slf4j
public class IpUtils {

    private static final String HEADER_X_REAL_IP = "X-Real-IP";
    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String HEADER_CLIENT_PROXY_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_CLIENT_PROXY_IP = "WL-Proxy-Client-IP";
    private static final String HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String IP_UNKNOWN = "unknown";
    private static final String COMMA = ",";

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件，则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-
     * For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(HEADER_X_REAL_IP);
        if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            // 使用 nginx 反向代理的情况下，配置了 X-REAL-IP 大概率可获取到 ip，意味着以下逻辑极小概率需要执行
            ip = request.getHeader(HEADER_X_FORWARDED_FOR);
            if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HEADER_CLIENT_PROXY_IP);
            }
            if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HEADER_WL_CLIENT_PROXY_IP);
            }
            if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HEADER_HTTP_CLIENT_IP);
            }
            if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HEADER_HTTP_X_FORWARDED_FOR);
            }
            if (StringUtils.isEmpty(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }

        int length = 15;
        // 使用代理，则获取第一个IP地址
        if (StringUtils.isNotBlank(ip) && ip.length() > length) {
            if (ip.indexOf(COMMA) > 0) {
                ip = ip.substring(0, ip.indexOf(COMMA));
            }
        }

        return ip;
    }

}
