package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.listener.UploadDataListener;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.query.OrderQuery;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import com.jourwon.spring.boot.service.OrderService;
import com.jourwon.spring.boot.util.EasyExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @PostMapping("/import_excel")
    @ApiOperation("导入订单Excel")
    public CommonResponse importExcel(MultipartFile file) {
        long start = System.currentTimeMillis();
        EasyExcelUtils.importExcel(file, OrderDTO.class, new UploadDataListener(orderService));
        log.info("导入数据耗时:{} ms", System.currentTimeMillis() - start);
        return CommonResponse.success();
    }

    @GetMapping("/export_excel")
    @ApiOperation("导出订单Excel")
    public void exportExcel(@Valid OrderQuery orderQuery, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        EasyExcelUtils.exportExcel(response, ("订单-" + start), "sheet1", OrderDTO.class, orderService.listOrder(orderQuery));
        log.info("导出数据耗时:{} ms", System.currentTimeMillis() - start);
    }

}
