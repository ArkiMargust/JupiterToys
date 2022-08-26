package Pages

import Modules.HeaderMenuModule
import geb.Page

class BasePage extends Page {

    static content = {

        inHeaderMenu { module HeaderMenuModule }

    }

}
