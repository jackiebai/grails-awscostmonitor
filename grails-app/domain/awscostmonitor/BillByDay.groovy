package awscostmonitor

class BillByDay {
    static mapWith = "mongo"
    static searchable = {
    	awsAccountId fielddata: true
    	product fielddata: true
    	environment fielddata: true
    	day index: "analyzed"
    	bill index: true
    }

    String awsAccountId
    String product
    String environment
    Date day
    double bill

    static constraints = {
    }
}