package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件controller
 *
 * @author JourWon
 * @date 2021/2/20
 */
@RequestMapping("/mail")
@RestController
public class EmailController {

    @Resource
    private MailService mailService;

    @GetMapping("/simple")
    public Map<String, Object> sendSimpleMail() {
        Map<String, Object> map = new HashMap<>(8);
        try {
            //参数就是接收邮件的邮箱，根据自己实际填写
            mailService.simpleMail("thinkwon@163.com");
            map.put("res", "success");
        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("res", "error");
        }
        return map;
    }

    @GetMapping("/htmlMail")
    public Map<String, Object> htmlMail() {
        Map<String, Object> map = new HashMap<>(8);
        try {
            mailService.htmlMail("thinkwon@163.com");
            map.put("res", "success");
        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("res", "error");
        }
        return map;
    }

    @GetMapping("/attachmentsMail")
    public Map<String, Object> attachmentsMail() {
        Map<String, Object> map = new HashMap<>(8);
        try {
            mailService.attachmentMail("thinkwon@163.com");
            map.put("res", "success");
        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("res", "error");
        }
        return map;
    }

    @GetMapping("/imgMail")
    public Map<String, Object> imgMail() {
        Map<String, Object> map = new HashMap<>(8);
        try {
            mailService.imgMail("thinkwon@163.com");
            map.put("res", "success");
        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("res", "error");
        }
        return map;
    }

    @GetMapping("/templateMail")
    public Map<String, Object> templateMail() {
        Map<String, Object> map = new HashMap<>(8);
        try {
            mailService.templateMail("thinkwon@163.com");
            map.put("res", "success");
        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("res", "error");
        }
        return map;
    }

}
