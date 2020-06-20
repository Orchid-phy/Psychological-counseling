package com.orchid.counselling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.orchid.counselling.mapper")
public class CounsellingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounsellingApplication.class, args);
    }

}
