package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.util.ZipUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件压缩
 *
 * @author JourWon
 * @date 2021/5/30
 */
@RestController
public class ZipController {

    @GetMapping("/zip")
    public void zip(HttpServletResponse response) {
        // ZipUtils.zip(response, "C:\\Users\\JourWon\\Desktop\\新建文件夹");
        // ZipUtils.zip(response,"C:\\Users\\JourWon\\Desktop\\订单数据1.xlsx");

        // ZipUtils.unzip("C:\\Users\\JourWon\\Desktop\\新建文件夹 (1).zip","C:\\Users\\JourWon\\Desktop\\新建文件夹 (1)");
        ZipUtils.unzip("C:\\Users\\JourWon\\Desktop\\订单数据1.zip","C:\\Users\\JourWon\\Desktop\\订单数据1");
    }

}
