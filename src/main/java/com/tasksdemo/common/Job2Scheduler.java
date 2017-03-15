package com.tasksdemo.common;

import com.tasksdemo.common.service.ISyncService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("scheduled.task2.enabled")
public class Job2Scheduler {
    private static final Logger log = LoggerFactory.getLogger(Job2Scheduler.class);
    private final ISyncService contactService;
    private final ISyncJob syncJob;

    @Autowired
    public Job2Scheduler(@Qualifier("nonBlockingJobWithSeparateExecutor") final ISyncJob syncJob, @Qualifier("service2") final ISyncService segmentService) {
        this.syncJob = syncJob;
        this.contactService = segmentService;
    }

    @Scheduled(cron = "${scheduled.task2}")
    public void doSync() {
        log.info("Started sync. at {}", new DateTime());
        syncJob.doSync(contactService);
        log.info("Ended sync. at {}", new DateTime());
    }
}
