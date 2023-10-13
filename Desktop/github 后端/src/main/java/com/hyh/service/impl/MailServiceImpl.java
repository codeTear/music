package com.hyh.service.impl;

import com.hyh.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    public boolean sendMail(String email, HttpSession session) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("star音乐验证码");// 主题
            // 获取一个随机的数
            String code = RandomCode();
            session.setAttribute("email", email);
            session.setAttribute("code", code);
            session.setMaxInactiveInterval(600);// 设置10分钟过期

            Context context = new Context();
            context.setVariable("code", code);

            String htmlContent = generateEmailHtml(context); // 生成HTML内容


            helper.setFrom("star音乐 <" + from + ">");


            helper.setText(htmlContent, true); // 设置邮件内容为HTML格式
            helper.setTo(email);// 验证码指定发给谁

            mailSender.send(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 随机生成的6位的随机数
    public String RandomCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            int nextInt = random.nextInt(10);
            stringBuilder.append(nextInt);
        }
        return stringBuilder.toString();
    }

    private String generateEmailHtml(Context context) {
        // 使用模板引擎生成HTML页面，将验证码填充到对应的位置
        return templateEngine.process("email_template", context);
    }
}
