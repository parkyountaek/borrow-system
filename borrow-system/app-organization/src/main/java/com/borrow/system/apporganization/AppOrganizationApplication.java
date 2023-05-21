package com.borrow.system.apporganization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {
        "com.borrow.system.modulecore.domain.organization"
})
@SpringBootApplication(scanBasePackages = {
        "com.borrow.system.modulecore.*",
        "com.borrow.system.modulecommon.*",
        "com.borrow.system.apporganization.*"
})
public class AppOrganizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppOrganizationApplication.class, args);
    }
}