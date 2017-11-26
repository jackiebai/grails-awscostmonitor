package awscostmonitor

class BootStrap {

    def cloudCheckrService
    def init = { servletContext ->cloudCheckrService.getHistoricalData()
    }
    def destroy = {
    }
}
