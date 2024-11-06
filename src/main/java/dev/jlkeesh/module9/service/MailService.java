package dev.jlkeesh.module9.service;

public interface MailService {
    void send(String to, String subject, String text);

    void sendOtp(String to, String otp);
}
