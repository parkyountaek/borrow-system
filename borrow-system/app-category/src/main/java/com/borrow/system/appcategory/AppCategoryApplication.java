package com.borrow.system.appcategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "com.borrow.system.apporganization.*",
        "com.borrow.system.appcategory.*"
})
@EntityScan(basePackages = {
        "com.borrow.system.modulecore.domain.organization",
        "com.borrow.system.modulecore.domain.category"
})
@SpringBootApplication(scanBasePackages = {
        "com.borrow.system.modulecore.*",
        "com.borrow.system.modulecommon.*",
        "com.borrow.system.appcategory.*",
        "com.borrow.system.apporganization.*"
})
public class AppCategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppCategoryApplication.class, args);
    }
}
