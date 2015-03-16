package com.ibmboc.server_conf.scheduleJob.examples.job;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by maven on 15/3/2.
 */
public class DumbJob implements Job {

    String jobSays;
    float myFloatValue;
    ArrayList state;

    public DumbJob() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();
        // Note the difference from the previous example
        state.add(new Date());
        System.err.println("Instance " + key + " of DumbJob says: "
                + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        state = state;
    }


    /*public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloat("myFloatValue");
        System.err.println("Instance " + key + " of DumbJob says: "
                + jobSays + ", and val is: " + myFloatValue);
    }*/

    /*public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();
        // Note the difference from the previous example
        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloat("myFloatValue");
        ArrayList state = (ArrayList) dataMap.get("myStateData");
        state.add(new Date());
        System.err.println("Instance " + key + " of DumbJob says: " + jobSays
                + ", and val is: " + myFloatValue);
    }*/

}