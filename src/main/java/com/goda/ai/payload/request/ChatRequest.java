package com.goda.ai.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ChatRequest {
    private String content;
    private String session_id;
    private double longitude;
    private double latitude;
}
