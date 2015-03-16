package com.ibmboc.server_conf.scheduleJob.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by maven on 15/3/4.
 */
public class AllTriggerListener implements TriggerListener {
    /**
     * <p>
     * Get the name of the <code>TriggerListener</code>.
     * </p>
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code>
     * has fired, and it's associated <code>{@link org.quartz.JobDetail}</code>
     * is about to be executed.
     * </p>
     * <p/>
     * <p>
     * It is called before the <code>vetoJobExecution(..)</code> method of this
     * interface.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has fired.
     * @param context The <code>JobExecutionContext</code> that will be passed to
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code>
     * has fired, and it's associated <code>{@link org.quartz.JobDetail}</code>
     * is about to be executed.  If the implementation vetos the execution (via
     * returning <code>true</code>), the job's execute method will not be called.
     * </p>
     * <p/>
     * <p>
     * It is called after the <code>triggerFired(..)</code> method of this
     * interface.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has fired.
     * @param context The <code>JobExecutionContext</code> that will be passed to
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code>
     * has misfired.
     * </p>
     * <p/>
     * <p>
     * Consideration should be given to how much time is spent in this method,
     * as it will affect all triggers that are misfiring.  If you have lots
     * of triggers misfiring at once, it could be an issue it this method
     * does a lot.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has misfired.
     */
    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code>
     * has fired, it's associated <code>{@link org.quartz.JobDetail}</code>
     * has been executed, and it's <code>triggered(xx)</code> method has been
     * called.
     * </p>
     *
     * @param trigger                The <code>Trigger</code> that was fired.
     * @param context                The <code>JobExecutionContext</code> that was passed to the
     *                               <code>Job</code>'s<code>execute(xx)</code> method.
     * @param triggerInstructionCode the result of the call on the <code>Trigger</code>'s<code>triggered(xx)</code>
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {

    }
}
