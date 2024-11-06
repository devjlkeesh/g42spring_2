package dev.jlkeesh.module9.service.impl;

import dev.jlkeesh.module9.dto.mail.Order;
import dev.jlkeesh.module9.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Override
    public void send(String to, String subject, String text) {

    }

    @Override
    @Async
    public void sendOtp(String to, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setFrom("MagicApp@mail.ru");
            mimeMessage.setRecipients(Message.RecipientType.TO, to);
            mimeMessage.setSubject("OTP for Magic App");
            Template template = configuration.getTemplate("otp.ftlh");
            List<Order> orders = new ArrayList<>();
            orders.add(new Order("123", "1", "qw", LocalDate.now(), "1"));
            orders.add(new Order("123", "1", "qw", LocalDate.now(), "1"));
            orders.add(new Order("123", "1", "qw", LocalDate.now(), "1"));
            orders.add(new Order("123", "1", "qw", LocalDate.now(), "1"));
            orders.add(new Order("123", "1", "qw", LocalDate.now(), "1"));

            Map<String, Object> model = Map.of(
                    "otp", otp,
                    "orders", orders);
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessage.setContent(content, "text/html;charset=utf-8");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
