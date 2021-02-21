package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件service实现类
 *
 * @author JourWon
 * @date 2021/2/20
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender jms;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void simpleMail(String toUser) {
        //初始化简单邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送者
        message.setFrom(from);
        //邮件接收者
        message.setTo(toUser);
        //邮件标题
        message.setSubject("简单邮件");
        //邮件内容
        message.setText("简单内容");
        //发送邮件
        jms.send(message);
    }

    @Override
    public void htmlMail(String toUser) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("html格式邮件");
        //内容为html格式
        String content = "<p style='color:red;'>这是一封html格式的文件</p><h1>这是一封html格式的文件</h1>";
        //true表示以html格式发送邮件
        helper.setText(content, true);
        jms.send(message);
    }

    @Override
    public void attachmentMail(String toUser) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("带附件邮件");
        //加载绝对路径资源
        FileSystemResource fs = new FileSystemResource(new File("D:\\IdeaProjects\\my-projects\\spring-boot-example\\spring-boot-email\\src\\main\\resources\\static\\Java开发手册（嵩山版）.pdf"));
        helper.setText("这是一封带附件的邮件！");
        //添加附件资源
        helper.addAttachment("Java开发手册（嵩山版）.pdf", fs);
        jms.send(message);
    }

    @Override
    public void imgMail(String toUser) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("带图片邮件");
        //设置资源的cid
        String content = "<html><body>博客头像<img src='cid:img'/></body></html>";
        helper.setText(content, true);
        FileSystemResource fs = new FileSystemResource(new File("D:\\IdeaProjects\\my-projects\\spring-boot-example\\spring-boot-email\\src\\main\\resources\\static\\excellent.jpg"));
        //和上边的cid要对应
        helper.addInline("img", fs);
        jms.send(message);
    }

    @Override
    public void templateMail(String toUser) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toUser);
        helper.setSubject("模板邮件");
        Context context = new Context();
        //给模板传入参数，username要与模板中变量名一致，thinkwon为测试数据
        context.setVariable("username", "thinkwon");
        //thymeleaf模板默认会从src/main/resources/tempaltes目录下寻找文件，填入我们定义的模板名，不需要写后缀。
        String template = templateEngine.process("MailTemplate", context);
        helper.setText(template, true);
        jms.send(message);
    }


}
