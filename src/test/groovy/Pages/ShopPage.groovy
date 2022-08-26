package Pages

import org.openqa.selenium.By

class ShopPage extends BasePage{

    static url = '/#/shop'

    static at = { products.asList().size() > 0 }

    static content = {
        products { $(".products.ng-scope") }
        buyProduct { String productName -> $(By.xpath("//*[text()='$productName']/following-sibling::*/child::*[@class='btn btn-success']")) }
    }


}
