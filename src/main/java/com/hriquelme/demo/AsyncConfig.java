package com.hriquelme.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(400);
        executor.setMaxPoolSize(4000);
        executor.setQueueCapacity(2000);
        executor.setKeepAliveSeconds(120);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}