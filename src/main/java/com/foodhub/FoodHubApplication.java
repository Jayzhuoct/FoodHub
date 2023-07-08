package com.foodhub;

import com.foodhub.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan//扫描Servlet
@EnableTransactionManagement//开启事务
public class FoodHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodHubApplication.class, args);
        log.info(" 美食汇（FoodHub）--项目启动成功...");
    }

}
