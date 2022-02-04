package models;

import java.sql.Timestamp;

public class Order extends Models {
    private int id;
    private KartItem[] kartItems;
    private Timestamp dateOrder;
    private boolean isPaid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KartItem[] getSelectedProducts() {
        return kartItems;
    }

    public void setSelectedProducts(KartItem[] kartItems) {
        this.kartItems = kartItems;
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
