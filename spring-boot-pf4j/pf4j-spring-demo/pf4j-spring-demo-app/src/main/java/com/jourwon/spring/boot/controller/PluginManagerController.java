package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginState;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 插件接口
 *
 * @author JourWon
 * @date 2021/9/13
 */
@RestController
@RequestMapping("/plugin")
@Api(tags = "插件接口")
public class PluginManagerController {

    private static final Logger log = LoggerFactory.getLogger(PluginManagerController.class);

    @Autowired
    private SpringPluginManager pluginManager;

    @GetMapping("/list")
    @ApiOperation("插件列表")
    public JsonResult listPlugins() {
        List<PluginWrapper> plugins = pluginManager.getPlugins();
        List<PluginDescriptor> descriptors = plugins.stream().map(PluginWrapper::getDescriptor).collect(Collectors.toList());
        return JsonResult.isOk(descriptors);
    }

    @GetMapping("/start")
    @ApiOperation("启用插件")
    public JsonResult startPlugin(@RequestParam("pluginId") String pluginId) {
        PluginState pluginState = pluginManager.startPlugin(pluginId);
        if (pluginState.equals(PluginState.STARTED)) {
            return JsonResult.isOk();
        } else {
            return JsonResult.isError("start plugin failed");
        }
    }

    @GetMapping("/stop")
    @ApiOperation("停用插件")
    public JsonResult stopPlugin(@RequestParam("pluginId") String pluginId) {
        PluginState pluginState = pluginManager.stopPlugin(pluginId);
        if (pluginState.equals(PluginState.STOPPED)) {
            return JsonResult.isOk();
        } else {
            return JsonResult.isError("stop plugin failed");
        }
    }

    @GetMapping("/load")
    @ApiOperation("加载插件")
    public JsonResult loadPlugin(@RequestParam("path") String path) throws Exception {
        String pluginId = pluginManager.loadPlugin(Paths.get(System.getProperty("user.dir"), path));
        return JsonResult.isOk(pluginId);
    }

    @GetMapping("/delete")
    @ApiOperation("删除插件")
    public JsonResult deletePlugin(@RequestParam("pluginId") String pluginId) {
        boolean result = pluginManager.deletePlugin(pluginId);
        if (result) {
            return JsonResult.isOk();
        } else {
            return JsonResult.isError();
        }
    }

}
