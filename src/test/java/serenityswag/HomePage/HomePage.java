package serenityswag.HomePage;

import net.serenitybdd.core.steps.UIInteractionSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HomePage extends UIInteractionSteps {

    public WebElement checkUserHeader() {
        return $(By.cssSelector("[alt='User']"));
    }
}
