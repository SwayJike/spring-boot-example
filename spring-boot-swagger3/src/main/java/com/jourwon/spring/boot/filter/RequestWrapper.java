package com.jourwon.spring.boot.filter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 解决HttpServletRequest inputStream只能读取一次的问题
 *
 * @author JourWon
 * @date 2021/1/22
 */
@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {

    private Cookie[] cookies;

    @Getter
    private final byte[] body;

    private final Map<String, String> headerMap = new HashMap<>();

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        body = getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        // 这里从body里面直接读了，没有去读inputStream了，很巧妙的方式
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    private String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.info("处理异常:{}", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.info("处理异常:{}", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.info("处理异常:{}", e);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    /**
     * 附加自定义的header
     *
     * @param name 名称
     * @param value 值
     */
    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        return headerMap.containsKey(name) ? headerMap.get(name) : super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if (headerMap.containsKey(name)) {
            return Collections.enumeration(Collections.singletonList(headerMap.get(name)));
        } else {
            return super.getHeaders(name);
        }
    }

}
