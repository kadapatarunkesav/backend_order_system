package com.backendordersystem.notification_service.DTO;

import java.util.UUID;

public record OutBoxEventDTO(

    String aggregateType,
    UUID  aggregateId,
    String payload,
    String type,
    boolean published
) {

}


// {
//   "id": "a1a9c048-ac15-49e4-911a-3fc2b3e4f6b3",
//   "aggregateType": "card-payment",
//   "aggregateId": "a9f1c47b-e3b5-4889-b909-1db500b71193",
//   "payload": "{\"userId\": \"tarunkesavk@gmail.com\"}",
//   "type": "PAYMENT_SUCCESS",
//   "published": false,
//   "createdAt": "2025-12-06T16:48:51.129155Z",
//   "publishedAt": null
// }
