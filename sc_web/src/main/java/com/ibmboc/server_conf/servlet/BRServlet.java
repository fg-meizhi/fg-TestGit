package com.ibmboc.server_conf.servlet;

import com.ibmboc.server_conf.facade.JobService;
import com.ibmboc.server_conf.scheduleJob.ScheduledJobRunner;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Backup & Restore Servlet
 * Created by maven on 15/2/15.
 */
public class BRServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(BRServlet.class);
    private String scriptRealPath;

    ScheduledJobRunner runner = new ScheduledJobRunner();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        scriptRealPath = config.getServletContext().getRealPath("/");
        logger.info("absolutPath = {}", scriptRealPath);
        scriptRealPath = scriptRealPath + "WEB-INF/classes/backupScripts/";
        logger.info("realPath = {}", scriptRealPath);

        try {
            runner.run();
        } catch (SchedulerException e) {
            logger.error("启动任务执行器失败，错误信息: {}", e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            runner.shutdown();
        } catch (SchedulerException e) {
            logger.error("关闭任务执行器失败，错误信息: {}", e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        String param = req.getParameter("param");

        JobService jobService = new JobService();
        JSONObject json = new JSONObject();

        if (StringUtils.isEmpty(param)) {
            logger.error("参数param为空");
        }

        if (param.equalsIgnoreCase("getJobList")) {
            json = jobService.getJobLogList();
        }

        if (param.equalsIgnoreCase("readLogFile")) {
            json = jobService.readLogFile(req.getParameter("fileName"));
//            logger.info("读取日志内容：{}", json);
        }

        /*if (param.equalsIgnoreCase("webag")) {
            try {
                Backuper.backupWebAG();
            } catch (Exception e) {
                logger.error("", e.getCause(), e);
            }
            return;
        }*/

        out(resp, json);
    }

    private void out(HttpServletResponse resp, JSONObject json) {
        resp.setContentType("text/html;charset=GB2312");//这条语句指明了向客户端发送的内容格式和采用的字符编码．
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.println(json);//利用PrintWriter对象的方法将数据发送给客户端

            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("输出返回信息发生错误，{}", e.getMessage(), e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }

}
