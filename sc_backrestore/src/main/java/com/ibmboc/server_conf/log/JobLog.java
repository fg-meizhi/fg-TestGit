package com.ibmboc.server_conf.log;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志全集
 * 用来解析日志，进行分块，供前端解析处理
 * Created by maven on 15/3/10.
 */
@Data
public class JobLog {
    List<LogLine> lines;

    public JobLog(){
        lines = new ArrayList<LogLine>();
    }
}
