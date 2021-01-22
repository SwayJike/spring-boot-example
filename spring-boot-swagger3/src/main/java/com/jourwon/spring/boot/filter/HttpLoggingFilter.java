package com.jourwon.spring.boot.filter;

import com.jourwon.spring.boot.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.UUID;

/**
 * 日志打印
 *
 * @author JourWon
 * @date 2021/1/22
 */
@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {

    private static final String BEFORE_LOG_URL = "请求地址 = {}";
    private static final String BEFORE_LOG_MSG = "请求参数 = {}";
    private static final String AFTER_LOG_MSG = "耗时[{}ms], 响应参数 = {}";
    private static final String MDC_KEY = "traceId";
    private static final String HEALTH = "health";
    private static final int LENGTH = 1000;

    private static final String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int length = 8;
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        //获取uri路径
        StringBuilder url = new StringBuilder();
        url.append("[").append(request.getMethod()).append("]");
        url.append("[").append(IpUtils.getIpAddr(request)).append("] ");
        url.append(request.getRequestURL());

        String queryString = request.getQueryString();
        if (null != queryString) {
            url.append("?").append(queryString);
        }

        String reqId = request.getHeader("reqId");
        if (StringUtils.isNotBlank(reqId)) {
            MDC.put(MDC_KEY, reqId);
        } else {
            reqId = generateShortUuid();
            MDC.put(MDC_KEY, reqId);
        }

        // 上传文件类的请求
        if (request.getContentType() == null
                || request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)
                || request.getContentType().contains(MediaType.MULTIPART_MIXED_VALUE)) {
            doLogBefore(url.toString(), "");
            filterChain.doFilter(request, response);
            doLogAfter(url.toString(), "", System.currentTimeMillis() - start);
            MDC.clear();
            return;
        }

        // 包装一层请求体
        RequestWrapper requestWrapper = new RequestWrapper(request);

        // 把reqId放到请求体里，供需要使用的服务获取
        requestWrapper.addHeader("reqId", reqId);

        //请求参数打印日志
        String requestBody = getContentAsString(requestWrapper.getBody(), requestWrapper.getCharacterEncoding());
        doLogBefore(url.toString(), requestBody);

        //执行真正的请求
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, wrappedResponse);

        //响应数据打印日志
        byte[] buf = wrappedResponse.getContentAsByteArray();
        String responseBody = getContentAsString(buf, response.getCharacterEncoding());
        doLogAfter(url.toString(), responseBody, System.currentTimeMillis() - start);

        //这里一定要把content拷贝回真正的response, copyBodyToResponse需要spring 4.2以上.
        wrappedResponse.copyBodyToResponse();
        MDC.clear();
    }

    private String getContentAsString(byte[] buf, String charsetName) {
        if (buf == null || buf.length == 0) {
            return "";
        }
        int length = Math.min(buf.length, LENGTH);
        try {
            String result = new String(buf, 0, length, charsetName);
            if (buf.length > LENGTH) {
                result = result + "...";
            }
            return result;
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    private void doLogBefore(String url, String payload) {
        if (!url.contains(HEALTH)) {
            log.info(BEFORE_LOG_URL, url);
            log.info(BEFORE_LOG_MSG, payload);
        }
    }

    private void doLogAfter(String url, String response, long time) {
        if (!url.contains(HEALTH)) {
            log.info(AFTER_LOG_MSG, time, Objects.isNull(response) ? "null" : response);
        }
    }

}
