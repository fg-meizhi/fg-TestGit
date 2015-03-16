package com.ibmboc.server_conf.scheduleJob.job;

import com.ibmboc.server_conf.executor.Runner;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by maven on 15/3/6.
 */
public class BackupJiraAppJob extends JobWraper implements Job {

    Logger logger = LoggerFactory.getLogger(BackupJiraAppJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String command = this.getScriptRealPath(jobExecutionContext) + "JIRA_appbak.bat";
        boolean result = Runner.run(command);

        if(!result){
            JobExecutionException e = new JobExecutionException("任务执行失败");
            throw e;
        }
    }
}

