package com.ibmboc.server_conf.executor;

import com.ibmboc.server_conf.config.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 备份器
 * Created by maven on 15/2/15.
 */
public class Backuper extends Runner {

    Logger logger = LoggerFactory.getLogger(Backuper.class);
    private String scriptRealPath;

    public Backuper(String realPath) {
        this.scriptRealPath = realPath;
    }

    public void backupAll() throws Exception {
        backupWebAG();
//        backupCAMAndCAS();
//        backupIntranet();
//        backupJIRA();
//        backupConfluence();
    }

    /**
     * 备份WebAG
     */
    public void backupWebAG() throws Exception {
        // 1. 备份WebAG程序
//        backupWebAGProgram();

        // 2.备份WebAG数据库
        backupWebAGDB();

        // 3.备份WebAG的FTP目录
//        backupWebAGFTP();
    }

    /**
     * 备份WebAG程序
     */
    private void backupWebAGProgram() {
        String command = scriptRealPath + ConfigUtil.get("webag.program");
        logger.info("备份WebAG程序，{}", command);
        run(command);
    }

    /**
     * 备份WebAG数据库
     */
    private void backupWebAGDB() {
        String command = scriptRealPath + "webag_db_backup.sh";

//        if(StringUtils.endsWithIgnoreCase(command, ".bat") || StringUtils.endsWithIgnoreCase(command, ".sh")){
//            File commandFile = new File(command)
//        }

        logger.info("=====Job Start=====备份WebAG数据库=====");
        run(command);
    }

    /**
     * 备份WebAG的FTP目录
     */
    private void backupWebAGFTP() {
        String command = scriptRealPath + ConfigUtil.get("webag.ftp");
        logger.info("备份WebAG的FTP目录，{}", command);
        run(command);
    }

    /**
     * 备份CAM
     */
    public void backupCAMAndCAS() {
        // 1. 备份CAM程序
        String command = ConfigUtil.get("cam.program");
        run(command);

        // 2. 备份CAS程序
        command = ConfigUtil.get("cas.program");
        run(command);

        // 3.备份CAM数据库
        command = ConfigUtil.get("cam.db");
        run(command);
    }

    /**
     * 备份Intranet
     */
    public void backupIntranet() {
        // 1. 备份Intranet程序
        String command = ConfigUtil.get("intranet.program");
        run(command);

        // 2.备份Intranet数据库
        command = ConfigUtil.get("intranet.db");
        run(command);
    }

    /**
     * JIRA
     */
    public void backupJIRA() {
        // 1. 备份JIRA程序
        String command = ConfigUtil.get("jira.program");
        run(command);

        // 2.备份JIRA数据库
        command = ConfigUtil.get("jira.db");
        run(command);

        // 3.备份JIRA的数据目录
        command = ConfigUtil.get("jira.data");
        run(command);
    }

    /**
     * Confluence
     */
    public void backupConfluence() {
        // 1. 备份Confluence程序
        String command = ConfigUtil.get("confluence.program");
        run(command);

        // 2.备份Confluence数据库
        command = ConfigUtil.get("confluence.db");
        run(command);

        // 3.备份Confluence的数据目录
        command = ConfigUtil.get("confluence.data");
        run(command);
    }
}
