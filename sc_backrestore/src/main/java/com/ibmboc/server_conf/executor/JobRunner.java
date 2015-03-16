package com.ibmboc.server_conf.executor;

import com.ibmboc.server_conf.config.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by maven on 15/2/27.
 */
public class JobRunner {

    Logger logger = LoggerFactory.getLogger(JobRunner.class);

    /**
     * 执行各种任务
     */
    public void run() {
        init();

        backup(getBackupScriptsRealPath());
        restore(getRestoreScriptsRealPath());

        close();
    }

    /**
     * 初始化任务日志记录器
     */
    private void init() {
        String jobLoggerFile = getRealPath() + "JobLogs" + File.separator + "JobLog_" + DateUtil.getCurrentTimeStr2() + ".log";
    }

    /**
     * 关闭任务日志记录器
     */
    private void close() {
    }

    /**
     * 备份任务
     */
    private void backup(String realPath) {
        Backuper backuper = new Backuper(realPath);
        try {
            backuper.backupWebAG();
        } catch (Exception e) {
            logger.error("", e.getMessage(), e);
        }
    }

    /**
     * 恢复任务
     */
    private void restore(String realPath) {
        Restorer restorer = new Restorer(realPath);
        try {
        } catch (Exception e) {
            logger.error("", e.getMessage(), e);
        }
    }

    /**
     * 获取当前程序的绝对路径
     * @return 返回绝对路径
     */
    private String getRealPath() {
        String realPath = String.valueOf(JobRunner.class.getClassLoader().getResource(""));
        realPath = StringUtils.startsWith(realPath, "file:") ? StringUtils.substring(realPath, 5) : realPath;
        logger.debug("程序绝对路径:{}", realPath);
        return realPath;
    }

    /**
     * 获取备份脚本命令的绝对路径
     * @return 备份脚本命令的绝对路径
     */
    private String getBackupScriptsRealPath() {
        String path = getRealPath() + "backupScripts" + File.separator;
        logger.info("备份脚本路径: {}", path);
        return path;
    }

    /**
     * 获取恢复脚本命令的绝对路径
     * @return 恢复脚本命令的绝对路径
     */
    private String getRestoreScriptsRealPath() {
        String path = getRealPath() + File.separator + "restoreScripts" + File.separator;
        logger.info("恢复脚本路径: {}", path);
        return path;
    }

    public static void main(String[] args) {
        JobRunner runner = new JobRunner();
        runner.run();
    }
}
