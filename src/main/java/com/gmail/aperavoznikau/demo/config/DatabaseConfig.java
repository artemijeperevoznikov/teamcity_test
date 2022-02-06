package com.gmail.aperavoznikau.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:db.properties")
public class DatabaseConfig {
}
