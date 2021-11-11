package serenityswag.InventoryPage;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

public class ViewProductDetailsActions extends UIInteractionSteps {

    ProductList productList;

    @Step("View Product details for product '{0}'")
    public void forProductWithName(String itemName) {
        $(ProductList.productDetailsLinkFor(itemName)).click();
    }
}
