package com.jourwon.spring.boot.service;

import javax.mail.MessagingException;

/**
 * 邮件service
 *
 * @author JourWon
 * @date 2021/2/20
 */
public interface MailService {

    /**
     * 简单文本邮件
     *
     * @param toUser 邮件接收者
     */
    void simpleMail(String toUser);

    /**
     * html邮件
     *
     * @param toUser 邮件接收者
     */
    void htmlMail(String toUser) throws MessagingException;

    /**
     * 带附件邮件
     *
     * @param toUser 邮件接收者
     */
    void attachmentMail(String toUser) throws MessagingException;

    /**
     * 带图片邮件
     *
     * @param toUser 邮件接收者
     */
    void imgMail(String toUser) throws MessagingException;

    /**
     * 模板邮件
     *
     * @param toUser 邮件接收者
     */
    void templateMail(String toUser) throws MessagingException;

}
