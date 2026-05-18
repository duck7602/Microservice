package com.ecommerce.notificationservice.service.impl;

import com.ecommerce.notificationservice.dto.response.NotificationResponse;
import com.ecommerce.notificationservice.mapper.NotificationMapper;
import com.ecommerce.notificationservice.repository.INotificationRepository;
import com.ecommerce.notificationservice.service.INotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NotificationService implements INotificationService {

    private final INotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public List<NotificationResponse> getAllPromotion() {
        return notificationMapper.toResponseList(notificationRepository.findAll());
    }

    @Override
    public void sendOrderSuccessEmail(String toEmail, Integer totalAmount) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Xác nhận đơn hàng #");
            String customerName = "Đức";
            String orderId = "111";
            Context context = new Context();
            context.setVariable("customerName", customerName);
            context.setVariable("orderId", orderId);
            context.setVariable("totalAmount", totalAmount);

            String htmlContent = templateEngine.process("order-confirmation", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("Email HTML đã gửi tới {}", toEmail);
        } catch (MessagingException e) {
            log.error("Gửi email thất bại: {}", e.getMessage());
        }
    }
}
