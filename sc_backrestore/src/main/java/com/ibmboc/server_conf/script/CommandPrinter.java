package com.ibmboc.server_conf.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by maven on 15/2/28.
 */
public class CommandPrinter {

    static Logger logger = LoggerFactory.getLogger(CommandPrinter.class);

    public static void print(String command) {
        File commandFile = new File(command);
        if(commandFile.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(commandFile));
                String line = "";

                logger.info("命令明细：");
                logger.info("-------------------------------------------------------------------------");

                while ((line = reader.readLine()) != null) {
                    logger.info(line);
                }

                logger.info("-------------------------------------------------------------------------");

            } catch (FileNotFoundException e) {
                logger.error("命令文件不存在，错误原因：{}", e.getMessage(), e);
            } catch (IOException e) {
                logger.error("读取命令文件失败，错误原因：{}", e.getMessage(), e);
            }
        } else {
            logger.info("命令：{}", command);
        }
    }



}
