package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import com.config.app.AppSecurityConfig;
import com.config.app.DBConfig;
import com.config.app.MyBatisConfig;

//@EnableWebMvc
@EnableAspectJAutoProxy
@Configuration
@Import({AppSecurityConfig.class, DBConfig.class, MyBatisConfig.class})
@ComponentScan({"com.test.service", "com.test.repository", "com.test.security"})
public class AppConfig {
	
}
