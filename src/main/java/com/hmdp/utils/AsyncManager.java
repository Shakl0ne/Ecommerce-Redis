package com.hmdp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncManager {
    private final int OPERATE_DELAY_TIME = 10;


    @Qualifier("scheduledExecutorService")
    @Autowired
    private ScheduledExecutorService executor;


    public void execute(Runnable task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}
