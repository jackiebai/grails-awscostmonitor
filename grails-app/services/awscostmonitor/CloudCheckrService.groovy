package awscostmonitor

import grails.transaction.Transactional

import grails.core.GrailsApplication
import org.apache.juli.logging.LogFactory
import com.xlson.groovycsv.CsvParser
import groovy.time.TimeCategory

@Transactional
class CloudCheckrService {

    private static final logger = LogFactory.getLog(this)
    GrailsApplication grailsApplication
    def httpClientService

    def getBillsByDay(prod, env, awsId, ccKey, startDate){
        def dateString = startDate[Calendar.MONTH]+1+'-'+startDate[Calendar.DATE] + '-' + startDate[Calendar.YEAR]
        def query = ['access_key': ccKey, 'aws_account_id': awsId, 'for_date': dateString]
        def json = makeCcRequest(query)
        if (json.BillByDay?.size > 0){
            for (def row:json.BillByDay) {
                def day = Date.parse(row['Day'])
                def bill = Double.valueOf(row['Bill'].replace('$', '').replace(',', '')).toDouble()
                def billByDay = BillByDay.findByAwsAccountIdAndDay(awsId, day)
                if (!billByDay) {
                    billByDay = new BillByDay(awsAccountId: awsId, product: prod, environment: env, bill: bill, day: day)
                } else {
                    billByDay.product = prod
                    billByDay.environment = env
                    billByDay.bill = bill
                }
                billByDay.save(flush: true, failOnError: true)
            }
        }
    }

    def makeCcRequest(query){
        def url = grailsApplication.config.getProperty('cloudcheckr.url')
        def path = grailsApplication.config.getProperty('cloudcheckr.path')
        try{
            httpClientService.callRestfulUrl(url,path,query)
        }
        catch(Exception ex){
            logger.error("CC All Fail: $ex.message")
        }
    }

    def getHistoricalData(){
        def startDate = Date.parse('MM-dd-yyyy',grailsApplication.config.getProperty('cloudcheckr.startDate'))
        def currentDate = new Date()
        println(startDate)
        while (startDate < currentDate){
            getData(startDate)
            use(TimeCategory) {
                startDate = startDate + 1.month
            }
        }
    }

    def getData(startDate){
        println(startDate)
        def file = grailsApplication.config.getProperty('cloudcheckr.accountInputFile')
        for(def line:CsvParser.parseCsv(new FileReader(file))){
            getBillsByDay(line.PRODUCT_NAME, line.ENVIRONMENT, line.AWS_ID,line.CLOUDCHECKR_KEY, startDate)
        }
    }
}
