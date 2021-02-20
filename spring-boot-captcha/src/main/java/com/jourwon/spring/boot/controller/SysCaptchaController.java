package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.ValidateSysCaptchaDTO;
import com.jourwon.spring.boot.service.SysCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码controller
 *
 * @author JourWon
 * @date 2021/2/19
 */
@RestController
@Api(tags = "验证码controller")
public class SysCaptchaController {

    @Resource
    private SysCaptchaService sysCaptchaService;

    /**
     * 获取验证码
     */
    @GetMapping("/captcha.jpg")
    @ApiOperation("获取验证码")
    public void captcha(HttpServletResponse response,@RequestParam String uuid) throws IOException {
        //禁止缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //设置响应格式为jpeg图片
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 校验验证码
     */
    @PostMapping("/validate")
    @ApiOperation("校验验证码")
    public boolean validate(@RequestBody ValidateSysCaptchaDTO validateSysCaptchaDTO) {
        return sysCaptchaService.validate(validateSysCaptchaDTO.getUuid(), validateSysCaptchaDTO.getCaptcha());
    }

}
