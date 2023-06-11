package com.borrow.system.appcategory.config;

import com.borrow.system.modulecore.config.QueryDslConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(QueryDslConfig.class)
public class AppCategoryConfiguration {
}