import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverLogLevel
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxDriverLogLevel
import org.openqa.selenium.firefox.FirefoxOptions

reportsDir = 'build/spock-reports/'

cacheDriver = true

atCheckWaiting = true

waiting {
    includeCauseInMessage = true
}

baseUrl = 'http://jupiter.cloud.planittesting.com'

environments {

    ChromeOptions optionsChrome = new ChromeOptions()
    FirefoxOptions optionsFirefox = new FirefoxOptions()
    EdgeOptions optionsEdge = new EdgeOptions()

    chrome {
        driver = {
            optionsChrome.setHeadless(System.getProperty("isHeadless").toBoolean())
            optionsChrome.setLogLevel(ChromeDriverLogLevel.OFF)
            optionsChrome.addArguments("--window-size=1920,1080")
            optionsChrome.addArguments("--incognito")
            optionsChrome.setAcceptInsecureCerts(true)
            new ChromeDriver(optionsChrome)
        }
    }

    firefox {
        driver = {
            optionsFirefox.setHeadless(System.getProperty("isHeadless").toBoolean())
            optionsFirefox.setLogLevel(FirefoxDriverLogLevel.ERROR)
            optionsFirefox.addArguments("-height=1080")
            optionsFirefox.addArguments("-width=1920")
            optionsFirefox.addArguments("-private")
            optionsFirefox.setAcceptInsecureCerts(true)
            new FirefoxDriver(optionsFirefox)
        }
    }

    edge {
        driver = {
            optionsEdge.setHeadless(System.getProperty("isHeadless").toBoolean())
            optionsEdge.addArguments("--window-size=1920,1080")
            optionsEdge.addArguments("-inprivate")
            optionsEdge.setAcceptInsecureCerts(true)
            new EdgeDriver(optionsEdge)
        }
    }
}
