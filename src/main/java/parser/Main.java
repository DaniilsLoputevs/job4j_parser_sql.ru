package parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Main class.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 04.05.20.
 */
public class Main implements Grab {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var config = new Config();
        var interval = Integer.valueOf(config.getValue("cron.time"));
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Logic.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            LOG.error(se.getMessage(), se);
        }
    }


    /**
     * Business logic og this App.
     * implements Job -> for describe logic that need to repeat by interval.
     */
    private static class Logic implements Job {
        private Config config = new Config();
        private static final Logger LOG = LoggerFactory.getLogger(Logic.class);

        /**
         * Describe all business logic.
         *
         * @param context - param by org.quartz
         * @throws JobExecutionException - org.quartz Exceptions.
         */
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            Parse parser = new Parser();
            var postsList = parser.list(config.getValue("target.url"));
            Store store = new PostgreSqlStore();
            store.saveAll(postsList);
            config.update();
        }
    }

}