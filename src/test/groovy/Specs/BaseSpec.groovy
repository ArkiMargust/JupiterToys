package Specs

import geb.spock.GebReportingSpec

class BaseSpec extends GebReportingSpec {

    void cleanup()  {
        clearCookies()
        quit()
    }

}
