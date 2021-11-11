package serenityswag.cart;

import java.util.Objects;

public final class CartItem {
    private final String title;
    private final String description;
    private final Double price;

    public CartItem(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public Double price() {
        return price;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (CartItem) obj;
//        return Objects.equals(this.title, that.title) &&
//                Objects.equals(this.description, that.description) &&
//                Objects.equals(this.price, that.price);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price);
    }

    @Override
    public String toString() {
        return "CartItem[" +
                "title=" + title + ", " +
                "description=" + description + ", " +
                "price=" + price + ']';
    }

}
