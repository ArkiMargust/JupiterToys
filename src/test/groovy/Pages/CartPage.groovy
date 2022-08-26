package Pages

import Modules.CartItemsModule

class CartPage extends BasePage{

    static url = '/#/cart'

    static at = { $(".alert").isDisplayed() || $(".btn.btn-danger.ng-scope").isDisplayed() }

    static content = {
        cartItems { $("table tr").tail().moduleList(CartItemsModule) }
        totalPrice { $(".total.ng-binding") }
    }

}
