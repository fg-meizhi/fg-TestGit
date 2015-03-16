package com.ibmboc.server_conf.log;

import lombok.Data;

/**
 * 日志行
 * Created by maven on 15/3/10.
 */
@Data
public class LogLine {

    int lineNumber;
    private String date;
    private String time;
    private String level;
    private String clazz;
    private String content;
    private boolean error;
//    private LogContent content = new LogContent();

}
