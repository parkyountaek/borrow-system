package com.borrow.system.apporganization.config;

import com.borrow.system.modulecore.config.QueryDslConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(QueryDslConfig.class)
public class AppOrganizationConfiguration {
}
