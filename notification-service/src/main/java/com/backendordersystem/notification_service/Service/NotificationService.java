package com.backendordersystem.notification_service.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.backendordersystem.notification_service.DTO.OutBoxEventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    public void sendMail(String message) throws Exception {

        OutBoxEventDTO boxEventDTO = objectMapper.readValue(message, OutBoxEventDTO.class);
        String userId = objectMapper
                .readTree(boxEventDTO.payload())
                .get("userId")
                .asText();
        SimpleMailMessage sendMail = new SimpleMailMessage();
        sendMail.setTo(userId);
        sendMail.setSubject("Order is ready to ship");
    }

}
