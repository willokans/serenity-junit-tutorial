package serenityswag.InventoryPage;

import net.thucydides.core.pages.PageObject;

public class InventoryPage extends PageObject {

    public String getHeading() {
        return $(".title").getText();
    }
}
