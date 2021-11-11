package serenityswag.cart;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.InventoryPage.ProductList;

import java.util.List;

public class CartActions extends UIInteractionSteps {

    ShoppingCartIcon shoppingCartIcon;

    @Step ("Add {0} to the cart")
    public void addItem(String itemName) {
        $(ProductList.addToCartButtonFor(itemName)).click();

    }

    @Step("Add multiple item to the cart")
    public void addItems(List<String> productTitle) {
        for (String product : productTitle){
            addItem(product);
        }
    }

    @Step("Open the shopping cart page")
    public void openCart() {
        $(shoppingCartIcon.link()).click();
    }

    public List<String> displayedItems() {
        return findAll(".inventory_item_name").texts();
    }
}
