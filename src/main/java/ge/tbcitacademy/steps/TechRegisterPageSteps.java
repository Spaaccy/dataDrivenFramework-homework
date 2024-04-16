package ge.tbcitacademy.steps;
import ge.tbcitacademy.pages.TechRegisterPage;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class TechRegisterPageSteps {
    TechRegisterPage techRegisterPage = new TechRegisterPage();

    public TechRegisterPageSteps fillUsername(String username) {
        techRegisterPage.firstName.sendKeys(username);
        return this;
    }
    public TechRegisterPageSteps fillLastName(String lastName) {
        techRegisterPage.lastName.sendKeys(lastName);
        return this;
    }
    public TechRegisterPageSteps selectGender(String gender) {
        techRegisterPage.gender.filter(value(gender)).first().click();
        return this;
    }
    public TechRegisterPageSteps selectModel(String model) {
        techRegisterPage.model.selectOption(model);
        return this;
    }
    public TechRegisterPageSteps fillCity(String city) {
        techRegisterPage.city.sendKeys(city);
        return this;
    }
    public TechRegisterPageSteps fillAddress1(String address1) {
        techRegisterPage.address1.sendKeys(address1);
        return this;
    }
    public TechRegisterPageSteps fillAddress2(String address2) {
        techRegisterPage.address2.sendKeys(address2);
        return this;
    }
    public TechRegisterPageSteps fillContact1(String contact1) {
        techRegisterPage.contact1.sendKeys(contact1);
        return this;
    }
    public TechRegisterPageSteps fillContact2(String contact2) {
        techRegisterPage.contact2.sendKeys(contact2);
        return this;
    }
    public void refreshPage() {
        refresh();
    }
}
