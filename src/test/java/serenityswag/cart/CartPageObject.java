package serenityswag.cart;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.ArrayList;
import java.util.List;

@DefaultUrl("https://www.saucedemo.com/cart.html")
public class CartPageObject extends PageObject {

    @FindBy(id = "checkout")
    WebElementFacade checkoutButton;

    @FindBy(id = ".title")
    WebElementFacade title;

    @FindBy(className = "cart_items")
    List<WebElementFacade> cartItemElements;

    public List<CartItem> items() {
        List<CartItem> cartItems = new ArrayList<>();
        for (WebElementFacade cartItemElement : cartItemElements){
            String name = cartItemElement.findBy(".inventory_item_name").getText();
            String description = cartItemElement.findBy(".inventory_item_desc").getText();
            Double price = priceForm(cartItemElement.findBy(".inventory_item_price").getText());
            cartItems.add(new CartItem(name, description, price));
        }
        return cartItems;
    }

    private Double priceForm(String textValue) {
        return Double.parseDouble(textValue.replace("$",""));
    }
}
