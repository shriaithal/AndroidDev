package storage.data.app.datastorage;

/**
 * Created by Shriaithal on 3/11/2018.
 */

public class Item {

    private String itemName;
    private String itemDescription;
    private String itemReview;
    private Double itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemReview() {
        return itemReview;
    }

    public void setItemReview(String itemReview) {
        this.itemReview = itemReview;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
