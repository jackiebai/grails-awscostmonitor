package awscostmonitor
import org.apache.commons.logging.LogFactory

class ImporterJob {
    private static final logger = LogFactory.getLog(this)
    def cloudCheckrService
    static triggers = {
        //simple name: 'mySimpleTrigger', startDelay: 180000, repeatInterval: 86400000, repeatCount: -1
        cron name: 'dailyTrigger', cronExpression: "0 0 5 ? * MON-SUN"
    }

    def execute() {
        // execute job
        logger.info('start a job')
        cloudCheckrService.getData(new Date())
    }
}

