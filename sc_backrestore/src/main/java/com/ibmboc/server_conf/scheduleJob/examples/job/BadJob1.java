package com.ibmboc.server_conf.scheduleJob.examples.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by maven on 15/3/2.
 */
public class BadJob1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            int zero = 0;
            int calculation = 4815 / zero;
        } catch (Exception e) {
            System.out.println("--- Error in job!");
            JobExecutionException e2 =
                    new JobExecutionException(e);
            // this job will refire immediately
            e2.refireImmediately();
            throw e2;
        }
    }
}
