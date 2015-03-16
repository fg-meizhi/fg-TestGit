package com.ibmboc.server_conf.scheduleJob;

import com.ibmboc.server_conf.config.ConfigUtil;
import com.ibmboc.server_conf.config.Constants;
import com.ibmboc.server_conf.config.DateUtil;
import com.ibmboc.server_conf.scheduleJob.job.*;
import com.ibmboc.server_conf.scheduleJob.listener.AllJobListener;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * Created by maven on 15/3/3.
 */
public class ScheduledJobRunner {

    Logger logger = LoggerFactory.getLogger(ScheduledJobRunner.class);
    Scheduler sched;

    public static void main(String[] args) {
        ScheduledJobRunner runner = new ScheduledJobRunner();
        try {
            runner.run();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void run() throws SchedulerException {
        sched = this.init();

        for (String groupName : sched.getJobGroupNames()) {
            logger.info("-----");

            logger.info("开始执行任务组：{}", groupName);

            for (JobKey key : sched.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupName))) {
                logger.info("待执行任务：{}", key);
            }

            logger.info("-----");
        }

        sched.start();
    }

    public void shutdown() throws SchedulerException {
        if (sched.isStarted()) {
            sched.shutdown();
        }
    }

    /**
     * 初始化任务计划器
     *
     * @return Scheduler
     * @throws SchedulerException
     */
    public Scheduler init() throws SchedulerException {
        String jobLoggerFile = ConfigUtil.get("Job_Log_Folder_Path") + File.separator + "JobLogInit_" + DateUtil.getCurrentTimeStr2() + ".log";

        SchedulerFactory sf = new StdSchedulerFactory();
        sched = sf.getScheduler();

        JobDataMap dataMap = initDataMap();

        // 执行备份任务
//        scheduleJob(dataMap, Constants.JobKey_Backup_WebAG_Program, BackupWebAGProgramJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_WebAG_DB, BackupWebAGDBJob.class);
//        scheduleJob(dataMap, Constants.JobKey_Backup_WebAG_FTP, BackupWebAGFTPJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_194mysql_file, Backup194MysqlFileJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_CAM_DB, BackupCamDBJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Jira_App, BackupJiraAppJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Jira_Data, BackupJiraDataJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Jira_DB, BackupJiraDBJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Confluence_App, BackupConfluenceAppJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Confluence_Data, BackupConfluenceDataJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Confluence_DB, BackupConfluenceDBJob.class);
        scheduleJob(dataMap, Constants.JobKey_Backup_Intranet_DB, BackupIntranetDBJob.class);
        //TODO:清理超期备份

        addListener();

        return sched;
    }

    /**
     * 初始化DataMap
     *
     * @return DataMap
     */
    private JobDataMap initDataMap() {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(Constants.BackupScriptRealPath, getBackupScriptsRealPath());
        dataMap.put(Constants.RestoreScriptRealPath, getRestoreScriptsRealPath());
        return dataMap;
    }

    /**
     * 创建Job和Trigger
     *
     * @param dataMap DataMap
     * @param key     Job 名
     * @param clazz   Job定义类
     * @throws SchedulerException
     */
    private void scheduleJob(JobDataMap dataMap, String key, Class clazz) throws SchedulerException {
        JobDetail job;
        CronTrigger trigger;
        job = newJob(clazz)
                .withIdentity(Constants.JobKeyPre + key, Constants.GroupJobKey)
                .usingJobData(dataMap)
                .build();
        trigger = newTrigger()
                .withIdentity(Constants.TriggerKeyPre + key, Constants.GroupTriggerKey)
                .withSchedule(cronSchedule(ConfigUtil.get(Constants.TriggerKeyPre + key)))
                .build();
        logger.info("JobKey:{}", key);
        sched.scheduleJob(job, trigger);
    }

    /**
     * 添加监听器
     *
     * @throws SchedulerException
     */
    private void addListener() throws SchedulerException {
        AllJobListener jobListener = new AllJobListener();
        sched.getListenerManager().addJobListener(jobListener, allJobs());
    }

    /**
     * 获取当前程序的绝对路径
     *
     * @return 返回绝对路径
     */
    private String getRealPath() {
        String realPath = String.valueOf(ScheduledJobRunner.class.getClassLoader().getResource(""));
        realPath = StringUtils.startsWith(realPath, "file:") ? StringUtils.substring(realPath, 5) : realPath;
        logger.debug("程序绝对路径:{}", realPath);
        return realPath;
    }

    /**
     * 获取备份脚本命令的绝对路径
     *
     * @return 备份脚本命令的绝对路径
     */
    private String getBackupScriptsRealPath() {
//        String path = getRealPath() + "backupScripts" + File.separator;
        String path = ConfigUtil.get("BackupScripts") + File.separator;
        logger.info("备份脚本路径: {}", path);
        return path;
    }

    /**
     * 获取恢复脚本命令的绝对路径
     *
     * @return 恢复脚本命令的绝对路径
     */
    private String getRestoreScriptsRealPath() {
//        String path = getRealPath() + "restoreScripts" + File.separator;
        String path = ConfigUtil.get("BackupScripts") + File.separator;
        logger.info("恢复脚本路径: {}", path);
        return path;
    }

}
