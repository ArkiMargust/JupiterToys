package Modules

import geb.Module

class HeaderMenuModule extends Module{

    static content = {

        selectMenu { String name -> $("*[href='#/${name}']") }

    }

}
