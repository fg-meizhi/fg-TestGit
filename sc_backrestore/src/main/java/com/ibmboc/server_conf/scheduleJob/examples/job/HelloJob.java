package com.ibmboc.server_conf.scheduleJob.examples.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by maven on 15/3/2.
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.printf("Hello! HelloJob is executing.");
    }
}
