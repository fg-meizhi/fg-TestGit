package com.ibmboc.server_conf.scheduleJob.job;

import com.ibmboc.server_conf.config.Constants;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 * Created by maven on 15/3/3.
 */
public class JobWraper {
    
    protected JobDataMap getDataMap(JobExecutionContext jobExecutionContext){
        return jobExecutionContext.getJobDetail().getJobDataMap();
    }

    protected String getScriptRealPath(JobExecutionContext jobExecutionContext) {
        return this.getDataMap(jobExecutionContext).getString(Constants.BackupScriptRealPath);
    }
}
