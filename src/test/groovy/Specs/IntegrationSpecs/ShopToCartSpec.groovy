package Specs.IntegrationSpecs

import Pages.CartPage
import Pages.ShopPage
import Specs.BaseSpec
import org.openqa.selenium.NoSuchSessionException
import spock.lang.Retry

@Retry(count = 5, exceptions=[NoSuchSessionException], condition = { failure.message.contains('Multiple Failures') })
class ShopToCartSpec extends BaseSpec{

    def "Customer Buys Products and Verifies the Prices"() {

        def expectedTotalPriceFormatted = expectedTotalPrice.contains(".") ? expectedTotalPrice.replaceAll('0*$',"").replaceAll('\\.$',"") : expectedTotalPrice

        given: "Customer is on Shop Page"
        to ShopPage

        when: "Customer Buys the following Products"
        at ShopPage
        2.times { buyProduct("Stuffed Frog").click() }
        5.times { buyProduct("Fluffy Bunny").click() }
        3.times { buyProduct("Valentine Bear").click() }

        and: "Customer Opens the Cart"
        inHeaderMenu.selectMenu("cart").click()

        then: "Customer makes the following calculations"
        at CartPage


        verifyAll {
            cartItems[0].productName == productName[0]
            cartItems[1].productName == productName[1]
            cartItems[2].productName == productName[2]

            cartItems[0].productPrice == productPrice[0]
            cartItems[1].productPrice == productPrice[1]
            cartItems[2].productPrice == productPrice[2]

            cartItems[0].productSubtotal == productSubtotal[0]
            cartItems[1].productSubtotal == productSubtotal[1]
            cartItems[2].productSubtotal == productSubtotal[2]

            totalPrice.text() == "Total: $expectedTotalPriceFormatted"
        }

        where:
        productName                                        | productPrice         | productQuantity || productSubtotal                                                                                                    || expectedTotalPrice
        ["Stuffed Frog", "Fluffy Bunny", "Valentine Bear"] | [10.99, 9.99, 14.99] | [2, 5, 3]       || [productPrice[0] * productQuantity[0], productPrice[1] * productQuantity[1], productPrice[2] * productQuantity[2]] || productSubtotal.sum().toString()

    }

}
