package com.hyh.controller;
import com.hyh.domain.User;
import com.hyh.service.impl.MailServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailServiceImpl mailService;
    /*
     * @description: 发送邮件
     * @param:
     * @return:
     * @author
     * @date: 2023/9/28 11:20
     */
    @PostMapping
    public String sendEmail(@RequestBody User user, HttpSession session){
        mailService.sendMail(user.getEmail(),session);
        return "success";
    }
}
