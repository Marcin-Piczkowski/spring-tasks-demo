package com.tasksdemo.common;

import com.tasksdemo.common.service.ISyncService;

public class SyncTask implements Runnable {

    private final ISyncService syncService;
    private final SyncConfig config;

    public SyncTask(final SyncConfig config, final ISyncService syncService) {
        this.config = config;
        this.syncService = syncService;
    }

    @Override
    public void run() {

        // Synchronize and notify
        syncService.doSync(config);
        // Finally change the Sync Tracking record status to finish
    }

}
