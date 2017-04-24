# spring-tasks-demo
Demo of Spring executors and scheduled jobs

There are 3 kinds of jobs/tasks execution:
* SyncJobBlockingImpl
* SyncJobNonBlocking
* SyncJobNonBlockingWithSeparateTaskExecutorImpl

To choose one of them change Job1Scheduler and Job2Scheduler constructor @Qualifier for ISyncJob

## Release
Release this project using Maven Release Plugin by doing the following steps:
1. mvn release:clean
2. mvn release:prepare
3. mvn release:perform
