package com.ibmboc.server_conf.scheduleJob.examples;

import com.ibmboc.server_conf.scheduleJob.examples.job.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by maven on 15/3/2.
 */
public class CronTriggerExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        //Job #1 is scheduled to run every 20 seconds
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/20 * * * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #2 is scheduled to run every other minute starting at 15 seconds past the minute.
        job = newJob(SimpleJob.class)
                .withIdentity("job2", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("15 0/2 * * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #3 is scheduled to run every other minute between 8am and 5pm (17 :00).
        job = newJob(SimpleJob.class)
                .withIdentity("job3", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #4 is scheduled to run every three minutes, but only between 5pm and 11pm
        job = newJob(SimpleJob.class)
                .withIdentity("job4", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger4", "group1")
                .withSchedule(cronSchedule("0 0/3 17-23 * * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #5 is scheduled to run at 10am on the 1st and 15th days of the month
        job = newJob(SimpleJob.class)
                .withIdentity("job5", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger5", "group1")
                .withSchedule(cronSchedule("0 0 10am 1,15 * ?"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #6 is scheduled to run every 30 seconds on weekdays (Monday through Friday)
        job = newJob(SimpleJob.class)
                .withIdentity("job6", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger6", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * MON-FRI"))
                .build();

        sched.scheduleJob(job, trigger);
        //Job #7 is scheduled to run every 30 seconds on weekends (Saturday and Sunday)
        job = newJob(SimpleJob.class)
                .withIdentity("job7", "group1")
                .build();

        trigger = newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * SAT,SUN"))
                .build();

        sched.scheduleJob(job, trigger);
        //The scheduler is then started (it also would have been fine to start it before scheduling the jobs).
        sched.start();
        //To let the scheduler have an opportunity to run the job, our program sleeps for five minutes (300 seconds). The scheduler is running in the background and should fire off several jobs during that time.
        Thread.sleep(300L * 1000L);
        //Finally, the program gracefully shuts down the scheduler:
        sched.shutdown(true);
    }

}
