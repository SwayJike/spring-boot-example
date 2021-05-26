package com.jourwon.spring.boot.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.model.vo.CommonResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
