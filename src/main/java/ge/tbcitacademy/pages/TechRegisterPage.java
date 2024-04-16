package ge.tbcitacademy.pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TechRegisterPage {
    public SelenideElement
     firstName = $(byValue("First Name")),
     lastName = $(byValue("Last Name")),
     address1 = $(byValue("Address1")),
     address2 = $(byValue("Address2")),
     city = $(byValue("City")),
     contact1 = $(byValue("Contact1")),
     contact2 = $(byValue("Contact2")),
     model = $(byName("model"));
    public ElementsCollection
     gender = $$(byName("gender"));
}
