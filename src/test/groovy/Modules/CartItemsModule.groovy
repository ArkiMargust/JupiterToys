package Modules

import geb.Module

class CartItemsModule extends Module {

    static content = {

        cell { $("td") }
        productName { cell.asList().get(0).text().trim() }
        productPrice { cell.asList().get(1).text().replace('$', "").toBigDecimal() }
        productQuantity { cell.asList().get(2).children().first().getAttribute("value").toInteger() }
        productSubtotal { cell.asList().get(3).text().replace('$', "").toBigDecimal() }

    }

}
