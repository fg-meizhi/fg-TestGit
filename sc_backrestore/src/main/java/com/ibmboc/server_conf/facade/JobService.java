package com.ibmboc.server_conf.facade;

import com.ibmboc.server_conf.config.ConfigUtil;
import com.ibmboc.server_conf.log.JobLog;
import com.ibmboc.server_conf.log.LogParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * 提供Job相关功能的服务
 * Created by maven on 15/3/4.
 */
public class JobService {

    Logger logger = LoggerFactory.getLogger(JobService.class);
    String jobLogFolderPath = ConfigUtil.get("logback_log_base");

    public JSONObject getJobLogList() {
        logger.debug("Job日志目录：{}", jobLogFolderPath);

        File jobLogFolder = new File(jobLogFolderPath);
        if (!jobLogFolder.isDirectory()) {
            logger.error("Job日志目录[{}]不是文件夹", jobLogFolderPath);
            return null;
        }

        File[] jobLogs = jobLogFolder.listFiles();
        Map<String, List<File>> fileMapByDate = storeFileInDateMap(jobLogs);

        Iterator<Map.Entry<String, List<File>>> it = fileMapByDate.entrySet().iterator();
        JSONObject json = new JSONObject();
        while(it.hasNext()){
            Map.Entry<String, List<File>> entry = it.next();
            String date = entry.getKey();
            List<File> files = entry.getValue();

            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < files.size(); i++) {
                File jobLog = files.get(i);
                String fileName = jobLog.getName();
                logger.debug("{}: {}", jobLog.isFile() ? "File" : "Folder", fileName);

                String[] namePartArr = fileName.split("\\.");
                String[] nameArr = namePartArr[0].split("_");
                String time = nameArr[2];
                time = time.replaceAll("-", ":");

                JSONObject logJson = new JSONObject();
                logJson.put("name", fileName);
                logJson.put("date", nameArr[1]);
                logJson.put("time", time);

                jsonArray.add(logJson);
            }

            json.put(entry.getKey(), jsonArray);
        }
        logger.debug("读取文件夹列表，以日期归类结果：{}", json);
        return json;
    }

    private Map<String, List<File>> storeFileInDateMap(File[] jobLogs) {
        Map<String, List<File>> fileMapByDate = new HashMap<String, List<File>>();
        for (File log : jobLogs) {
            if(log.isFile()){
                String date = getFileDate(log);
                fileMapByDate.put(date, new ArrayList());
            }
        }
        for (File log : jobLogs) {
            if(log.isFile()){
                String date = getFileDate(log);
                fileMapByDate.get(date).add(log);
            }
        }
        return fileMapByDate;
    }

    private String getFileDate(File log) {
        String fileName = log.getName();
        String[] namePartArr = fileName.split("\\.");
        String[] nameArr = namePartArr[0].split("_");
        return nameArr[2];
    }

    public JSONObject readLogFile(String fileName) {

        String filePath = jobLogFolderPath + fileName;
        logger.debug("Job日志目录：{}", jobLogFolderPath);
        logger.debug("Job日志：{}", filePath);

        JSONObject json = new JSONObject();
        String key = "content";

        File logFile = new File(filePath);
        if (!logFile.exists()) {
            logger.error("Job日志不存在，日志路径:{}", filePath);
            json.put(key, "Job日志不存在，日志路径:" + filePath);
        }

        JobLog jobLog = LogParser.parse(logFile);
        json.put(key, JSONObject.fromObject(jobLog));

        return json;
    }

    public static void main(String[] args) {
        JobService service = new JobService();
        System.out.println(service.getJobLogList());
    }

}
