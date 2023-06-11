package com.borrow.system.appitem.appborrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "com.borrow.system.appcategory.*",
        "com.borrow.system.apporganization.*",
        "com.borrow.system.appusermanagement.*",
        "com.borrow.system.appitem.*",
        "com.borrow.system.appborrow.*"
})
@EntityScan(basePackages = {
        "com.borrow.system.modulecore.domain.borrow",
        "com.borrow.system.modulecore.domain.category",
        "com.borrow.system.modulecore.domain.user",
        "com.borrow.system.modulecore.domain.organization",
        "com.borrow.system.modulecore.domain.item"
})
@SpringBootApplication(scanBasePackages = {
        "com.borrow.system.modulecore.*",
        "com.borrow.system.modulecommon.*",
        "com.borrow.system.appcategory.*",
        "com.borrow.system.apporganization.*",
        "com.borrow.system.appusermanagement.*",
        "com.borrow.system.appitem.*",
        "com.borrow.system.appborrow.*"
})
public class AppBorrowApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppBorrowApplication.class, args);
    }
}
