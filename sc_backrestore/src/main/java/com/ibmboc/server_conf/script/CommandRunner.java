package com.ibmboc.server_conf.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 命令执行器
 * Created by maven on 15/2/15.
 */
public class CommandRunner {

    static Logger logger = LoggerFactory.getLogger(CommandRunner.class);

    public static boolean runScript(Process process) {
        boolean result = false;
        try {

            StreamPrinter outputPrinter = new StreamPrinter(process.getInputStream(), "OUTPUT");
            StreamPrinter errorPrinter = new StreamPrinter(process.getErrorStream(), "ERROR");

            outputPrinter.start();
            errorPrinter.start();

            result = execute(process);
        } catch (InterruptedException e) {
            logger.error("读取Exit Value时发生错误, {}", e.getMessage(), e);
        }
        return result;
    }

    public static boolean runScript(Process process, String filePath) {
        boolean result = false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);

            StreamPrinter outputPrinter = new StreamPrinter(process.getInputStream(), "OUTPUT", fos);
            StreamPrinter errorPrinter = new StreamPrinter(process.getErrorStream(), "ERROR");

            outputPrinter.start();
            errorPrinter.start();

            result = execute(process);

            fos.flush();
            fos.close();
        } catch (InterruptedException e) {
            logger.error("读取Exit Value时发生错误, {}", e.getMessage(), e);
        } catch (FileNotFoundException e) {
            logger.error("输出文件时发生错误, {}", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("关闭输出文件时发生错误, {}", e.getMessage(), e);
        } finally {
            try {
                if (null != fos) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                logger.error("关闭输出文件时发生错误, {}", e.getMessage(), e);
            }
        }
        return result;
    }

    private static boolean execute(Process process) throws InterruptedException {
        boolean result = false;
        double start = System.currentTimeMillis();

        int exitVal = process.waitFor();

        double end = System.currentTimeMillis();
        double time = (end - start) / 1000;

        switch (exitVal) {
            case 0:
                result = true;
                logger.info("命令执行完毕，正常退出，用时{}秒", time);
                break;
            default:
                logger.info("程序执行失败，返回值:{}，用时{}秒", exitVal, time);
                break;
        }

        return result;
    }
}
