package com.jourwon.spring.boot.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.query.OrderQuery;
import com.jourwon.spring.boot.service.OrderService;
import com.jourwon.spring.boot.util.EasyExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 订单controller
 *
 * @author JourWon
 * @date 2021/5/16
 */
@Slf4j
@Api(tags = "订单")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/import")
    @ApiOperation("导入订单Excel")
    public ResponseEntity<?> importExcel(MultipartHttpServletRequest request) throws Exception {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        for (Map.Entry<String, MultipartFile> part : fileMap.entrySet()) {
            InputStream inputStream = part.getValue().getInputStream();
            Map<Integer, String> head = new HashMap<>(16);
            List<Map<Integer, String>> data = new LinkedList<>();
            EasyExcel.read(inputStream).sheet()
                    .registerReadListener(new AnalysisEventListener<Map<Integer, String>>() {
                        @Override
                        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                            head.putAll(headMap);
                        }

                        @Override
                        public void invoke(Map<Integer, String> row, AnalysisContext analysisContext) {
                            data.add(row);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            log.info("读取文件[{}]成功,一共:{}行......", part.getKey(), data.size());
                        }
                    }).doRead();
            // 其他业务逻辑
        }
        return ResponseEntity.ok("success");
    }


    @GetMapping(path = "/download")
    public void download(HttpServletResponse response) throws Exception {

        String fileName = URLEncoder.encode("文件名.xlsx", StandardCharsets.UTF_8.toString());
        // 封装标题行
        List<List<String>> head = new ArrayList<>();
        // 封装数据
        List<List<String>> data = new LinkedList<>();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream())
                .head(head)
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("Sheet名字")
                .doWrite(data);
    }

    @GetMapping("/export")
    @ApiOperation("导出订单Excel")
    public void exportExcel(@Valid OrderQuery orderQuery, HttpServletResponse response) {
        try {
            long start = System.currentTimeMillis();
            EasyExcelUtils.download(response, "", OrderDTO.class, orderService.listOrder(orderQuery));
            log.info("导出数据耗时:{} ms", System.currentTimeMillis() - start);
        } catch (IOException e) {
            throw new RuntimeException("导出excel错误", e);
        }
    }

}
