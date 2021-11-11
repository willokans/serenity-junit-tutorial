package serenityswag.InventoryPage;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementState;

public class ProductDetails extends PageObject {

    public String productName() {
        return $(".inventory_details_name").getText();
    }

    public WebElementState productImageWithAltValueOf(String itemName) {
        return  $(".inventory_details_container img[alt='"+itemName+"']");
    }
}
