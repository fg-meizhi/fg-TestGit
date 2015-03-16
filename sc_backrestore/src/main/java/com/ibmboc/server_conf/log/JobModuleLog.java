package com.ibmboc.server_conf.log;

import lombok.Data;

import java.util.List;

/**
 * Created by maven on 15/3/10.
 */
@Data
public class JobModuleLog {
    private LogLine startLine;
    private List<LogLine> lines;
    private LogLine endLine;
}
