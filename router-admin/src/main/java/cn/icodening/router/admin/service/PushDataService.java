package cn.icodening.router.admin.service;

import cn.icodening.router.admin.model.PushData;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @author icodening
 * @date 2021.04.19
 */
@Service
public class PushDataService {

    private final ThreadPoolTaskExecutor taskExecutor;

    public PushDataService() {
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("router-push");
        taskExecutor.initialize();
    }

    public void pushData(PushData pushData) {
        ListenableFuture<?> listenableFuture = taskExecutor.submitListenable(() -> {
            //推送数据到指定服务
        });
        listenableFuture.addCallback((SuccessCallback<Object>) object -> {

        }, throwable -> {

        });
    }
}
