package com.ibmboc.server_conf.scheduleJob.examples;

import com.ibmboc.server_conf.scheduleJob.examples.job.BadJob1;
import com.ibmboc.server_conf.scheduleJob.examples.job.BadJob2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by maven on 15/3/2.
 */
public class JobExceptionExample {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        Date startTime = new Date();

        //Job #1 is scheduled to run every 3 seconds indefinitely. This job will fire BadJob1.
        JobDetail job = newJob(BadJob1.class)
                .withIdentity("badJob1", "group1")
                .build();

        SimpleTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        Date ft = sched.scheduleJob(job, trigger);

        //Job #2 is scheduled to run every 3 seconds indefinitely. This job will fire BadJob2.
        job = newJob(BadJob2.class)
                .withIdentity("badJob2", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger2", "group1")
                .startAt(startTime)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        ft = sched.scheduleJob(job, trigger);

//        The scheduler is then started.
        sched.start();
//        To let the scheduler have an opportunity to run the job, our program will sleep for 1 minute (60 seconds)
        Thread.sleep(60L * 1000L);
//        The scheduler will run both jobs (BadJob1 and BadJob2). Each job will throw an exception. Job 1 will attempt to refire immediately. Job 2 will never run again.
//        Finally, the program gracefully shuts down the scheduler:
        sched.shutdown(true);
    }
}
