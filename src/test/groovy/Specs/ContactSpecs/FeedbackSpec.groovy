package Specs.ContactSpecs

import Pages.ContactPage
import Specs.BaseSpec
import geb.error.RequiredPageContentNotPresent
import org.openqa.selenium.NoSuchSessionException
import spock.lang.Retry
import spock.lang.Rollup

class FeedbackSpec extends BaseSpec {

    @Rollup()
    def "Customer Submits a Feedback"() {

        given: "Customer is on Contact Page"
        to ContactPage

        when: "Customer submits a feedback"
        at ContactPage

        fillFeedbackFormAndSubmit(specificationContext)

        then: "Contact Page Welcome Message is displayed"
        confirmationMessage.text() == expectedMessage

        where:
        forename | email           | message || expectedMessage
        "Run 1"  | "run1@test.com" | "Run 1" || "Thanks $forename, we appreciate your feedback."
        "Run 2"  | "run2@test.com" | "Run 2" || "Thanks $forename, we appreciate your feedback."
        "Run 3"  | "run3@test.com" | "Run 3" || "Thanks $forename, we appreciate your feedback."
        "Run 4"  | "run4@test.com" | "Run 4" || "Thanks $forename, we appreciate your feedback."
        "Run 5"  | "run5@test.com" | "Run 5" || "Thanks $forename, we appreciate your feedback."

    }

    def "Customer Skips Required Fields then rectifies mistakes"() {

        given: "Customer is on Contact Page"
        to ContactPage

        when: "Customer submits a blank feedback"
        at ContactPage
        submitButton.click()

        then: "Expected Error Messages should be displayed"
        verifyAll {
            errorMessages.every { it.displayed }
            expectedErrorMessages == errorMessages.collect { it.text() }.sort()
        }

        when: "Customer Correctly Fills Feedback Form"
        fillFeedbackForm(specificationContext)
        errorMessages.every { it.displayed }

        then: "Error Messages should not be Present"
        thrown(RequiredPageContentNotPresent)

        where:
        forename = 'This Forename'
        email = 'this@email.com'
        message ='This message'
        expectedErrorMessages = [
                "Forename is required",
                "Email is required",
                "Message is required",
                "We welcome your feedback - but we won't get it unless you complete the form correctly."
        ].sort()
    }
}
