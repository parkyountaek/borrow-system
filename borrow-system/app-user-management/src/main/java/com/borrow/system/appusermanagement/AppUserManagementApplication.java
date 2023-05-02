package com.borrow.system.appusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.borrow.system.modulecore.user.*"})
@SpringBootApplication(scanBasePackages = {
        "com.borrow.system.modulecore.*",
        "com.borrow.system.modulecommon.*",
        "com.borrow.system.appusermanagement.*"
})
public class AppUserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppUserManagementApplication.class, args);
    }
}
