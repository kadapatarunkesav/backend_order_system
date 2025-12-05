package com.backendordersystem.notification_service.Entity;

public class Notification {

     @NotBlank
        private final String correlationId;
        @NotBlank
        @Email
        private final String to;
        @NotBlank
        private final String subject;
        @NotBlank
        private final String body;
        private final Map<String, Object> metadata;

}
