package com.jourwon.spring.boot.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Druid状态controller
 *
 * @author JourWon
 * @date 2021/2/10
 */
@Api(tags = "Druid状态监控")
@RestController
public class DruidStatController {

    @GetMapping("/druid/stat")
    @ApiOperation("Druid状态")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

}
