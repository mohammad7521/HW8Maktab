package models;

import java.sql.Date;

public class Order extends Models {
    private int id;
    private SelectedProduct[] selectedProducts;
    private Date dateOrder;
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

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
