package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.dto.ValidationSysCaptchaDTO;
import com.jourwon.spring.boot.service.SysCaptchaService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
public class SysCaptchaController {

    @Resource
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
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
    @PostMapping("/validation")
    public boolean login(@RequestBody ValidationSysCaptchaDTO validationSysCaptchaDTO) {
        return sysCaptchaService.validation(validationSysCaptchaDTO.getUuid(), validationSysCaptchaDTO.getCaptcha());
    }

}
