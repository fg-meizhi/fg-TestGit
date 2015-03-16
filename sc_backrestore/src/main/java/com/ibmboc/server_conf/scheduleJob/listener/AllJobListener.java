package com.ibmboc.server_conf.scheduleJob.listener;

import com.ibmboc.server_conf.config.Constants;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by maven on 15/3/3.
 */
public class AllJobListener implements JobListener {

    Logger logger = LoggerFactory.getLogger(AllJobListener.class);

    @Override
    public String getName() {
        return "AllJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
//        jobLogger.print("即将执行Job：%s", key);
//        logger.info("即将执行Job：{}", key);

        String keyName = key.getName().replace(Constants.JobKeyPre, "");
        keyName = Constants.JobKeyMap.get(keyName);

        logger.info("=====Job Start====={}=====", keyName);

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        logger.info("开始执行Job：{}", key);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
//        jobLogger.print("执行完成Job：%s", key);
//        logger.info("执行完成Job：{}", key);

        String keyName = key.getName().replace(Constants.JobKeyPre, "");
        keyName = Constants.JobKeyMap.get(keyName);

        logger.info("=====Job Finished====={}=====", keyName);
    }
}
