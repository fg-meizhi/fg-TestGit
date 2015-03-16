package com.ibmboc.server_conf.log;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将文本日志解析成对象
 * Created by maven on 15/3/10.
 */
public class LogParser {

    static Logger logger = LoggerFactory.getLogger(LogParser.class);

    /**
     * 解析文本为日志对象
     *
     * @param logFile 文本文件
     * @return 日志对象
     */
    public static JobLog parse(File logFile) {

        JobLog jobLog = new JobLog();
        BufferedReader reader = null;
        String line = "";
        String stdLogRegx = "\\[\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\]\\s\\[\\w+\\]\\s(\\w*\\.*\\w*)*\\[\\d+\\]";
        try {
            reader = new BufferedReader(new FileReader(logFile));
            int num = 1;
            while (null != (line = reader.readLine())) {
//                logger.info(num+"");
                LogLine logLine = new LogLine();

                Pattern pattern = Pattern.compile(stdLogRegx);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
//                    logger.info("matches:{}", matcher.matches());
                    logLine = parseLine(line);
                } else {
                    logLine.setContent(new String(line.getBytes(), "UTF-8"));
                    logLine.setError(true);
                }
                logLine.setLineNumber(num++);
                jobLog.lines.add(logLine);

//                logger.info(logLine.toString());
            }

            reader.close();
        } catch (FileNotFoundException e) {
            logger.error("读取日志文件失败, {}", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("关闭读取日志文件流失败, {}", e.getMessage(), e);
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                logger.error("关闭读取日志文件流失败, {}", e.getMessage(), e);
            }
        }

        return jobLog;
    }

    /**
     * 解析日志行
     *
     * @param line 文本行
     * @return 文本行对象
     */
    private static LogLine parseLine(String line) {
        LogLine logLine = new LogLine();
        String[] words = line.split(" ");
        logLine.setDate(words[0]/*.replace("[", "")*/);
        logLine.setTime(words[1]/*.replace("]", "")*/);
        logLine.setLevel(words[2]);
        logLine.setClazz(words[3]);

        if (StringUtils.indexOf(words[2], "[ERROR]") > 0 || StringUtils.indexOf(words[4], "ERROR >") > 0) {
            logLine.setError(true);
        }

        String inforPart = words[0] + words[1] + words[2] + words[3];
        int contentStart = inforPart.length() + 4;

        String content = line.substring(contentStart);
        try {
            logLine.setContent(new String(content.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("处理日志行编码发生错误，{}", e.getMessage(), e);
        }
        return logLine;
    }


    public static void main(String[] args) {
        File file = new File("/Users/maven/Downloads/temp/LogBack_WorkingLog_2015-03-12_110030.log");
        JobLog log = LogParser.parse(file);
        logger.info(log.toString());
    }
}
