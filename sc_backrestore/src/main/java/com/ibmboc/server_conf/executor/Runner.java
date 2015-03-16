package com.ibmboc.server_conf.executor;

import com.ibmboc.server_conf.script.CommandPrinter;
import com.ibmboc.server_conf.script.CommandRunner;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 本地脚本执行器
 * Created by maven on 15/2/15.
 */
public class Runner {
    static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static boolean run(String command) {
        boolean result = false;
        logger.info("执行命令：{}", command);

        CommandPrinter.print(command);

        Process process = null;
        boolean redirectFlag = false;
        String filePath = "";

        try {
            if (StringUtils.indexOf(command, " ") > 0) {
                String[] commandArr = command.split(" ");
                filePath = handlerOutputFile(command, filePath, commandArr);

                logger.info("拆解命令数组：{}", Arrays.toString(commandArr));
                process = Runtime.getRuntime().exec(commandArr);
            } else {
                process = Runtime.getRuntime().exec(command);
            }
        } catch (IOException e) {
            logger.info("执行命令失败: {}", e.getMessage(), e);
        }

        if (redirectFlag)
            result = CommandRunner.runScript(process, filePath);
        else
            result = CommandRunner.runScript(process);

        return result;
    }

    private static String handlerOutputFile(String command, String filePath, String[] commandArr) {
        List<String> commandList = new ArrayList<String>();

        if (StringUtils.indexOf(command, ">") > 0) {
            for (int i = 0; i < commandArr.length; i++) {
                String c = commandArr[i];
                if (c.equalsIgnoreCase(">")) {
                    filePath = commandArr[i + 1];
                    logger.info("写入文件路径：{}", filePath);
                    break;
                }
                commandList.add(c);
            }
        }

        return filePath;
    }
}
