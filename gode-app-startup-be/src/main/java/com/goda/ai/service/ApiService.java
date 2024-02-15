package com.goda.ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.goda.ai.payload.request.ChatRequest;

@Service
public class ApiService {

    
    private RestTemplate restTemplate;

    public String callApi(ChatRequest request) {
        String url = "http://localhost:8000/chat"; // Replace with your URL
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(request);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
