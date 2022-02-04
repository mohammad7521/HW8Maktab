package models;

import java.sql.Date;
import java.sql.Timestamp;

public class Order extends Models {
    private int id;
    private SelectedProduct[] selectedProducts;
    private Timestamp dateOrder;
    private boolean isPaid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SelectedProduct[] getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(SelectedProduct[] selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
