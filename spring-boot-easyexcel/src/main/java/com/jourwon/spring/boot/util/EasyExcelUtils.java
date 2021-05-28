package com.jourwon.spring.boot.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.enums.ModelTypeEnum;
import com.jourwon.spring.boot.listener.ModelExcelListener;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel 工具类
 *
 * @author JourWon
 * @date 2021/5/25
 */
@Slf4j
public class EasyExcelUtils {

    /**
     * 读取Excel(一个sheet)
     *
     * @param file     文件
     * @param clazz    实体类
     * @param listener excel读取监听器
     */
    public static void readExcel(MultipartFile file, Class<?> clazz, ReadListener<?> listener) {
        try {
            EasyExcel.read(file.getInputStream(), clazz, listener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel(一个sheet)
     *
     * @param file          文件
     * @param clazz         实体类
     * @param modelTypeEnum sheet的模型类型
     * @return List<T>
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> clazz, ModelTypeEnum modelTypeEnum) {
        ModelExcelListener<T> listener = new ModelExcelListener<>(modelTypeEnum);

        try {
            EasyExcel.read(file.getInputStream(), clazz, listener)
                    .sheet(modelTypeEnum.getSheetName())
                    .doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listener.getList();
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
    public static <T> void writeExcel(HttpServletResponse response, String fileName, String sheetName, Class<T> clazz, List<T> list) {
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

        CommonResponse<?> failure = CommonResponse.failure(CommonResponseCodeEnum.FAILED_DOWNLOAD_FILE);
        try {
            response.getWriter().println(JSON.toJSONString(failure));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 Excel(多个 sheet)
     */
    public static <T> List<T> readExcel(ExcelReader reader, Class<T> rowModel, int sheetCount) {
        if (reader == null) {
            return new ArrayList<>();
        }
        List<ReadSheet> readSheetList = new ArrayList<>();
        ModelExcelListener<T> excelListener = new ModelExcelListener<>();
        ReadSheet readSheet = EasyExcel.readSheet(sheetCount)
                .head(rowModel)
                .registerReadListener(excelListener)
                .build();
        readSheetList.add(readSheet);
        reader.read(readSheetList);
        return getExtendsBeanList(excelListener.getList(), rowModel);
    }

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     *
     * @param inputStream 文件输入流
     * @param rowModel    实体类映射
     */
    private static List[] readExcel(InputStream inputStream, Integer sheetNo, Class<?>[] rowModel) {
        ExcelReader reader = getReader(inputStream);
        if (reader == null) {
            return new ArrayList[rowModel.length];
        }
        List[] result = new ArrayList[rowModel.length];
        for (int sheetCount = 0; sheetCount < rowModel.length; sheetCount++) {
            if (sheetNo != null && sheetNo != sheetCount) {
                continue;
            }
            result[sheetCount].addAll(readExcel(reader, rowModel[sheetCount], sheetCount));
        }
        return result;
    }

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     *
     * @param inputStream 输入流
     * @param rowModel    实体类映射
     */
    public static List[] readExcel(InputStream inputStream, Class<?>... rowModel) {
        ExcelReader reader = getReader(inputStream);
        if (reader == null) {
            return new ArrayList[rowModel.length];
        }
        List[] result = new ArrayList[rowModel.length];
        for (int sheetCount = 0; sheetCount < rowModel.length; sheetCount++) {
            result[sheetCount] = new ArrayList<>(readExcel(reader, rowModel[sheetCount], sheetCount));
        }
        return result;
    }

    /**
     * 读取 Excel(单个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     */
    public static <T> List<T> readFirstSheetExcel(InputStream inputStream, Class<T> rowType) {
        ExcelReader reader = getReader(inputStream);
        if (reader == null) {
            return new ArrayList<>();
        }
        return readExcel(reader, rowType, 0);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param inputStream 文件输入流
     * @param rowModel    实体类映射
     * @param sheetNo     sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T> List readExcel(InputStream inputStream, Class<T> rowModel, int sheetNo) {
        Class[] classes = {rowModel};
        return readExcel(inputStream, sheetNo, classes)[0];
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     *
     * @param outputStream 文件输出流
     * @param list         数据 list
     * @param sheetName    导入文件的 sheet 名
     */
    public static <T> void writeExcel(OutputStream outputStream, List<T> list, String sheetName) {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "Sheet1";
        }
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        EasyExcel.write(outputStream, list.get(0).getClass()).sheet(sheetName).doWrite(list);
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     *
     * @param outputStream 文件输出流
     * @param list         数据 list
     * @param fileName     导出的文件名
     */
    public static <T> void writeExcel(OutputStream outputStream, List<T> list,
                                      String fileName, String sheetName) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        sheetName = StringUtils.isNotBlank(sheetName) ? sheetName : "Sheet1";
        EasyExcel.write(outputStream, list.get(0).getClass()).sheet(sheetName).doWrite(list);
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     */
    public static void writeExcel(OutputStream outputStream,String sheetName, List... lists) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(outputStream).build();
            for (int count = 0; count < lists.length; count++) {
                if (CollectionUtils.isEmpty(lists[count])) {
                    continue;
                }
                sheetName = StringUtils.isNotBlank(sheetName) ? sheetName : "Sheet" + (count + 1);
                WriteSheet writeSheet = EasyExcel.writerSheet(count, sheetName)
                        .head(lists[count].get(0).getClass())
                        .build();
                excelWriter.write(lists[count], writeSheet);
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 返回 ExcelReader
     */
    public static ExcelReader getReader(InputStream inputStream) {
        return EasyExcel.read(inputStream).build();
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T> List<T> getExtendsBeanList(List<?> list, Class<T> typeClazz) {
        return BeanTransformUtils.transformList(list, typeClazz);
    }

}
