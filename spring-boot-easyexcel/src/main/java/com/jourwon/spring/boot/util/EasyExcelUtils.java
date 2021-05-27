package com.jourwon.spring.boot.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * EasyExcel 工具类
 *
 * @author JourWon
 * @date 2021/5/25
 */
public class EasyExcelUtils {

    /**
     * 读取Excel（一个sheet）
     *  @param file   文件
     * @param clazz   实体类
     * @param listener   excel读取监听器
     */
    public static void importExcel(MultipartFile file, Class<?> clazz, ReadListener listener) {
        try {
            EasyExcel.read(file.getInputStream(), clazz, listener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出Excel(一个sheet)
     *
     * @param response  HttpServletResponse
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的sheet名
     * @param clazz     实体类
     * @param list      数据list
     */
    public static <T> void exportExcel(HttpServletResponse response, String fileName, String sheetName, Class<T> clazz, List<T> list) {
        try {
            OutputStream outputStream = getOutputStream(response, fileName);

            // 这里需要设置不关闭流
            EasyExcel.write(outputStream, clazz)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet(sheetName)
                    .doWrite(list);
        } catch (IOException e) {
            responseFailure(response);
        }
    }

    /**
     * 获取OutputStream
     *
     * @param response HttpServletResponse
     * @param fileName fileName
     * @return OutputStream
     */
    private static OutputStream getOutputStream(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fn = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition",
                "attachment;filename=" + fn + ExcelTypeEnum.XLSX.getValue());
        return response.getOutputStream();
    }

    /**
     * 响应错误信息
     *
     * @param response HttpServletResponse
     */
    private static void responseFailure(HttpServletResponse response) {
        // 重置response
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        CommonResponse failure = CommonResponse.failure(CommonResponseCodeEnum.FAILED_DOWNLOAD_FILE);
        try {
            response.getWriter().println(JSON.toJSONString(failure));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(HttpServletResponse response, String fileName,
                                Class cls, List dataList) throws IOException {
        // 这里注意 有同学反应使用 swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fn = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + fn + ExcelTypeEnum.XLSX.getValue());

            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), cls)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("sheet1")
                    .doWrite(dataList);
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            CommonResponse failure = CommonResponse.failure(CommonResponseCodeEnum.FAILED_DOWNLOAD_FILE);
            response.getWriter().println(JSON.toJSONString(failure));
        }
    }

}
