package com.tasksdemo.common;

import com.tasksdemo.common.service.ISyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * This SyncJob will wait for all tasks to complete before doSync method exits.
 * It uses the same ExecutorService for tasks and jobs (common thread pool).
 */
@Component("nonBlockingJobWithSeparateExecutor")
public class SyncJobNonBlockingWithSeparateTaskExecutorImpl implements ISyncJob {
    private static final Logger log = LoggerFactory.getLogger(SyncJobNonBlockingWithSeparateTaskExecutorImpl.class);

    private final TaskExecutor executor;

    @Autowired
    public SyncJobNonBlockingWithSeparateTaskExecutorImpl(@Qualifier("jobTaskExecutor") final TaskExecutor executor) {
        this.executor = executor;
    }

    public void doSync(ISyncService syncService) {
        IntStream.range(0, 5).forEach(i -> {

            log.info("Sync job for site {}", i);
            IntStream mls = IntStream.range(0, 10);
            mls.forEach(j -> {
                log.debug("ML: {} ", j);
                SyncConfig config = new SyncConfig();
                config.setSite(i);
                config.setMl(j);
                Runnable worker = new SyncTask(config, syncService);
                executor.execute(worker);
            });

        });
    }

}