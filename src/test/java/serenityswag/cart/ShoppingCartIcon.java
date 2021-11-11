package serenityswag.cart;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class ShoppingCartIcon extends PageObject {
    public String badgeCount() {
        return $(".shopping_cart_link").getText();
    }

    public By link(){
        return By.cssSelector(".shopping_cart_link");
    }
}
