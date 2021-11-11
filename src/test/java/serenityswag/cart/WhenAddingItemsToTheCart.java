package serenityswag.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenityswag.InventoryPage.ProductList;
import serenityswag.authentication.LoginActions;
import serenityswag.cart.CartActions;
import serenityswag.cart.ShoppingCartIcon;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@RunWith(SerenityRunner.class)
public class WhenAddingItemsToTheCart {

    @Managed (driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    @Before
    public void login(){
        login.as(STANDARD_USER);
    }

    ShoppingCartIcon shoppingCartBadge;

    ProductList productList;

    @Test
    public void theCorrectItemCountShouldBeShown(){
        Serenity.reportThat("The shopping cart badge should be empty",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEmpty()
        );
        cart.addItem("Sauce Labs Backpack");
        Serenity.reportThat("The Shopping cart badge should be '1'",
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo("1")
        );
    }

    @Test
    public void allTheItemShouldAppearInTheCart(){
        login.as(STANDARD_USER);

        List<String> selectedItems = firstThreeProductTitleDisplayed();

        cart.addItems(selectedItems);

        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The Shopping cart badge should be "+cartBadgeCount,
                () -> assertThat(shoppingCartBadge.badgeCount()).isEqualTo(cartBadgeCount)
        );

        cart.openCart();
        Serenity.reportThat("I should see items added to the cart",
                () -> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
        );
    }

    CartPageObject cartPage;
    @Test
    public void priceForEachItemShouldBeShownInTheCart() {
        login.as(STANDARD_USER);

        //add items to shopping cart
        cart.addItems(firstThreeProductTitleDisplayed());

        //Open the cart page
        cartPage.open();

        //Check that each item in the cart has a price
        List<CartItem> items = cartPage.items();

        assertThat(items).hasSize(3)
                .allMatch(item -> item.price() > 0.0)
                .allMatch(item -> !item.description().isEmpty());

    }

    private List<String> firstThreeProductTitleDisplayed() {
        return productList.titles().subList(0, 3);
    }
}
