package com.ibmboc.server_conf.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by maven on 15/2/26.
 */
public class StreamPrinter extends Thread {

    static Logger logger = LoggerFactory.getLogger(StreamPrinter.class);
    InputStream is;
    String type;
    OutputStream os;

    StreamPrinter(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    StreamPrinter(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public void run() {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
        String line = "";

        PrintWriter pw = null;
        if (os != null)
            pw = new PrintWriter(os);

        try {
            while ((line = reader.readLine()) != null) {
                logger.info("{} > {}", type, line);

                if (pw != null)
                    pw.println(line);
            }

            if (pw != null)
                pw.flush();
        } catch (IOException e) {
            logger.error("读取[{}]流信息失败, {}", type, e.getMessage(), e);
        }
    }
}
