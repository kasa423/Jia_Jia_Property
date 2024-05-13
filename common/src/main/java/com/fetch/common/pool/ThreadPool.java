package com.fetch.common.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/5/11 14:24
 */
@Component
public class ThreadPool {

    @Bean
    public ExecutorService executorService(){
        return new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue
                <>(1000), new ThreadPoolExecutor.AbortPolicy());
    }
}
