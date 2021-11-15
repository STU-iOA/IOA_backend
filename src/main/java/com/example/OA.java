package com.example;
import com.example.generator.services.OAService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OA implements ApplicationRunner {
    @Autowired
    OAService oaService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        oaService.insertOA();
    }
}
