package com.hyh.service;

import jakarta.servlet.http.HttpSession;

public interface MailService {
    public boolean sendMail(String email, HttpSession session);
    public String RandomCode();
}
