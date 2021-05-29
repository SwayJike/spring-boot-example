package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.enums.CommonResponseCodeEnum;
import com.jourwon.spring.boot.enums.RowModelEnum;
import com.jourwon.spring.boot.listener.UploadDataListener;
import com.jourwon.spring.boot.model.dto.OrderDTO;
import com.jourwon.spring.boot.model.entity.Order;
import com.jourwon.spring.boot.model.query.OrderQuery;
import com.jourwon.spring.boot.model.vo.CommonResponse;
import com.jourwon.spring.boot.service.OrderService;
import com.jourwon.spring.boot.util.BeanTransformUtils;
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
import java.io.IOException;
import java.util.List;

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
    public CommonResponse<?> importExcel(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return CommonResponse.failure(CommonResponseCodeEnum.FILE_EMPTY);
        }

        long start = System.currentTimeMillis();
        try {
            EasyExcelUtils.readExcel(file.getInputStream(), OrderDTO.class, new UploadDataListener(orderService));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("导入数据耗时:{} ms", System.currentTimeMillis() - start);
        return CommonResponse.success();
    }

    @PostMapping("/import_excel2")
    @ApiOperation("导入订单Excel2")
    public CommonResponse<?> importExcel2(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return CommonResponse.failure(CommonResponseCodeEnum.FILE_EMPTY);
        }

        long start = System.currentTimeMillis();
        List<OrderDTO> list = null;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), OrderDTO.class, RowModelEnum.ORDER_SECOND.getSheetName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Order> orderList = BeanTransformUtils.transformList(list, Order.class);
        orderService.saveBatch(orderList);
        log.info("导入数据耗时:{} ms", System.currentTimeMillis() - start);
        return CommonResponse.success();
    }

    @PostMapping("/import_excel3")
    @ApiOperation("导入订单Excel3")
    public CommonResponse<?> importExcel3(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return CommonResponse.failure(CommonResponseCodeEnum.FILE_EMPTY);
        }

        long start = System.currentTimeMillis();
        List<OrderDTO> list = null;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), OrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Order> orderList = BeanTransformUtils.transformList(list, Order.class);
        orderService.saveBatch(orderList);

        log.info("导入数据耗时:{} ms", System.currentTimeMillis() - start);
        return CommonResponse.success();
    }

    @PostMapping("/import_excel4")
    @ApiOperation("导入订单Excel4")
    public CommonResponse<?> importExcel4(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return CommonResponse.failure(CommonResponseCodeEnum.FILE_EMPTY);
        }

        long start = System.currentTimeMillis();
        List<OrderDTO>[] list = null;
        try {
            list = EasyExcelUtils.readExcel(file.getInputStream(), new Class[]{OrderDTO.class});
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert list != null;
        for (List<OrderDTO> orders : list) {
            List<Order> orderList = BeanTransformUtils.transformList(orders, Order.class);
            orderService.saveBatch(orderList);
        }

        log.info("导入数据耗时:{} ms", System.currentTimeMillis() - start);
        return CommonResponse.success();
    }

    @GetMapping("/export_excel")
    @ApiOperation("导出订单Excel")
    public void exportExcel(@Valid OrderQuery orderQuery, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        EasyExcelUtils.writeExcel(response, ("订单-" + start), "Sheet1", orderService.listOrder(orderQuery));
        log.info("导出数据耗时:{} ms", System.currentTimeMillis() - start);
    }

    @GetMapping("/export_excel2")
    @ApiOperation("导出订单Excel2")
    public void exportExcel2(@Valid OrderQuery orderQuery, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        List<OrderDTO> list = orderService.listOrder(orderQuery);
        EasyExcelUtils.writeExcel(response, ("订单-" + start), null, new List[]{list.subList(0, 10), list.subList(10, 20)});
        log.info("导出数据耗时:{} ms", System.currentTimeMillis() - start);
    }

}
