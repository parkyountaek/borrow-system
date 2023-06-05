package com.borrow.system.appitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "com.borrow.system.apporganization.*",
        "com.borrow.system.appcategory.*",
        "com.borrow.system.appitem.*"
})
@EntityScan(basePackages = {
        "com.borrow.system.modulecore.domain.item",
        "com.borrow.system.modulecore.domain.category",
        "com.borrow.system.modulecore.domain.organization"
})
@SpringBootApplication(scanBasePackages = {
        "com.borrow.system.appitem.*",
        "com.borrow.system.appcategory.*",
        "com.borrow.system.apporganization.*",
        "com.borrow.system.modulecore.*",
        "com.borrow.system.modulecommon.*"
})
public class AppItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppItemApplication.class, args);
    }
}