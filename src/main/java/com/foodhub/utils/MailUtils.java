package com.foodhub.utils;

import java.lang.String;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * 邮件注册工具类
 */
public class MailUtils {
    public static void sendMessage(JavaMailSender sender,String code ,String mailName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【FoodHub 美食汇】注册邮件");
        message.setText("尊敬的用户：\n" +
                "感谢您注册本站账号！\n请输入下面验证码完成注册:\n" +
                code +
                "\n如非本人操作，请忽略此邮件。\n" +
                "祝您生活愉快！\n" +
                "FoodHub 美食汇团队");
        message.setTo(mailName);
        message.setFrom("850717623@qq.com");
        sender.send(message);
        System.out.println("发送成功！");
    }
}