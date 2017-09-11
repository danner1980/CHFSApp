package dan.hunt.com.chfsapp;

/**
 * Created by dan on 26/08/17.
 */

public class ProductInfo {
    String barcode;
    String description;
    String datelastsold;
    String price;

    ProductInfo() {
        barcode = "";
        description = "";
        datelastsold = "";
        price = "";
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatelastsold() {
        return datelastsold;
    }

    public void setDatelastsold(String datelastsold) {
        this.datelastsold = datelastsold;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
