package com.ibmboc.server_conf.scheduleJob.job;

import com.ibmboc.server_conf.executor.Runner;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by maven on 15/3/3.
 */
public class BackupWebAGFTPJob extends JobWraper implements Job {

    Logger logger = LoggerFactory.getLogger(BackupWebAGFTPJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String command = this.getScriptRealPath(jobExecutionContext) + "webag_ftp_backup.sh";
        boolean result = Runner.run(command);

        if(!result){
            JobExecutionException e = new JobExecutionException("任务执行失败");
            throw e;
        }
    }
}
