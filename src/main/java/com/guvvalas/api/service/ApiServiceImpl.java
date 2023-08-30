package com.guvvalas.api.service;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Builder
public class ApiServiceImpl {

    Consumer<String> preHook;

    Consumer<String> postHook;

    public void process(String req){
        Optional.ofNullable(preHook).ifPresent(p->p.accept(req));
        log.info("process.......{}",req);
        Optional.ofNullable(postHook).ifPresent(p->p.accept("res->"+req));
    }
}
