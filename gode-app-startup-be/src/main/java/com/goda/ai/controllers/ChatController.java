package com.goda.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goda.ai.payload.request.ChatRequest;
import com.goda.ai.service.ApiService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ApiService apiService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public String callApi(@RequestBody ChatRequest request) {
        return apiService.callApi(request);
    }

}
