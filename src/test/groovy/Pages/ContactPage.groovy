package Pages

import geb.module.EmailInput
import geb.module.TextInput
import geb.module.Textarea
import org.spockframework.runtime.SpecificationContext

class ContactPage extends BasePage {

    static url = '/#/contact'

    static at = { $(id: "header-message").text().equalsIgnoreCase("We welcome your feedback - tell it how it is.") }

    static content = {
        fieldName { String label -> $(id: label) }
        forenameField { $(id: "forename").module(TextInput) }
        emailField { $(id: "email").module(EmailInput) }
        messageTextArea { $(id: "message").module(Textarea) }
        submitButton { $(".btn-contact.btn.btn-primary") }
        loadingModal(wait: true, required: false) { $("*[aria-hidden='false']") }
        confirmationMessage(wait: true) { $(".alert.alert-success") }
        errorMessages { $(".alert.alert-error.ng-scope, .help-inline.ng-scope") }
    }

    void fillFeedbackFormAndSubmit(SpecificationContext values) {

        forenameField.text = values.currentIteration.dataVariables.get("forename")
        emailField.text = values.currentIteration.dataVariables.get("email")
        messageTextArea.text = values.currentIteration.dataVariables.get("message")
        submitButton.click()
        waitFor(60) { !loadingModal.displayed }
    }

    void fillFeedbackForm(SpecificationContext values) {

        forenameField.text = values.currentIteration.dataVariables.get("forename")
        emailField.text = values.currentIteration.dataVariables.get("email")
        messageTextArea.text = values.currentIteration.dataVariables.get("message")
    }
}
