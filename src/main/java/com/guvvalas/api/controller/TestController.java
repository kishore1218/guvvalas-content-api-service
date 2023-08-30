package com.guvvalas.api.controller;

import com.guvvalas.api.repository.CourseRepository;
import com.guvvalas.api.service.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @Autowired
    private CourseRepository repo;

    @Autowired
    ApiServiceImpl apiService;

    @GetMapping("/health")
    public ResponseEntity<Mono<String>> health(){
       var mono=Mono.just("success");
        return ResponseEntity.ok(mono);

    }
}
