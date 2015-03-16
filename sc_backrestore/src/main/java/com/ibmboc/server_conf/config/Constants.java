package com.ibmboc.server_conf.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maven on 15/3/4.
 */
public class Constants {
    public static final String BackupScriptRealPath = "BackupScriptRealPath";
    public static final String RestoreScriptRealPath = "RestoreScriptRealPath";
    public static final String JobLogger = "jobLogger";

    public static final String GroupJobKey = "Job_BackUp";
    public static final String GroupTriggerKey = "Trigger_Backup";

    public static final String JobKeyPre = "Job_";
    public static final String TriggerKeyPre = "Trigger_";

    public static final String JobKey_Create_Joblog = "Create_Joblog";
    public static final String JobKey_Backup_WebAG_Program = "Backup_WebAG_Program";
    public static final String JobKey_Backup_WebAG_DB = "Backup_WebAG_DB";
    public static final String JobKey_Backup_WebAG_FTP = "Backup_WebAG_FTP";
    public static final String JobKey_Backup_194mysql_file = "Backup_194mysql_file";
    public static final String JobKey_Backup_CAM_DB = "Backup_CAM_DB";
    public static final String JobKey_Backup_Jira_App = "Backup_Jira_App";
    public static final String JobKey_Backup_Jira_Data = "Backup_Jira_Data";
    public static final String JobKey_Backup_Jira_DB = "Backup_Jira_DB";
    public static final String JobKey_Backup_Confluence_App = "Backup_Confluence_App";
    public static final String JobKey_Backup_Confluence_Data = "Backup_Confluence_Data";
    public static final String JobKey_Backup_Confluence_DB = "Backup_Confluence_DB";
    public static final String JobKey_Backup_Intranet_DB = "Backup_Intranet_DB";

    public static final Map<String, String> JobKeyMap = new HashMap();

    static {
        JobKeyMap.put(JobKey_Backup_WebAG_Program, "备份WebAG程序");
        JobKeyMap.put(JobKey_Backup_WebAG_DB, "备份WebAG数据库");
        JobKeyMap.put(JobKey_Backup_WebAG_FTP, "备份WebAG的FTP文件");
        JobKeyMap.put(JobKey_Backup_194mysql_file, "备份197MySQL文件");
        JobKeyMap.put(JobKey_Backup_CAM_DB, "备份CAM数据库");
        JobKeyMap.put(JobKey_Backup_Jira_App, "备份JIRA程序文件");
        JobKeyMap.put(JobKey_Backup_Jira_Data, "备份JIRA数据文件");
        JobKeyMap.put(JobKey_Backup_Jira_DB, "备份JIRA数据库");
        JobKeyMap.put(JobKey_Backup_Confluence_App, "备份Confluence程序文件");
        JobKeyMap.put(JobKey_Backup_Confluence_Data, "备份Confluence数据文件");
        JobKeyMap.put(JobKey_Backup_Confluence_DB, "备份Confluence数据库");
        JobKeyMap.put(JobKey_Backup_Intranet_DB, "备份Intranet数据库");
    }
}
