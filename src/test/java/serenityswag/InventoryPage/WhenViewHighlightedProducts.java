package serenityswag.InventoryPage;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.LoginActions;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static serenityswag.authentication.User.STANDARD_USER;

@RunWith(SerenityRunner.class)
public class WhenViewHighlightedProducts {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    ViewProductDetailsActions viewProductDetails;

    ProductList productList;

    ProductDetails productDetails;

    @Test
    public void shouldDisplayHighLightedProductOnTheWelcomePage() {
        login.as(STANDARD_USER);

        List<String> productOnDisplay = productList.titles();
        assertThat(productOnDisplay)
                .hasSize(6)
                .contains("Sauce Labs Backpack");
    }

    @Test
    public void highLightedProductShouldDisplayTheCorrespondingImg(){
        login.as(STANDARD_USER);
        List<String> productOnDisplay = productList.titles();

        SoftAssertions softly = new SoftAssertions();
        productOnDisplay.forEach(productName -> softly.assertThat(productList
                .imageTextForProduct(productName))
                .isEqualTo(productName));
        softly.assertAll();
    }

    @Test
    public void shouldDisplayCorrectProductDetailPage() {
        login.as(STANDARD_USER);

        String firstItemName = productList.titles().get(0);
        viewProductDetails.forProductWithName(firstItemName);

        Serenity.reportThat("The product name should be displayed",
                () ->assertThat(productDetails.productName()).isEqualTo(firstItemName));
        Serenity.reportThat("The product image should have to correct alt text",
                () -> productDetails.productImageWithAltValueOf(firstItemName).shouldBeCurrentlyVisible());
    }
}
