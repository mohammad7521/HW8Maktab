package models;

import services.ProductServices;

public class KartItem extends Models {

    private int id;
    private Product product=new Product();
    private int selectedQuantity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public void setProductID(int productID){
        this.product.setId(productID);
    }

    public int getProductID(){
        return this.product.getId();
    }

    @Override
    public String toString() {
        return "\nproduct:" + ProductServices.showInfo(product.getId()).getName() +
                ", selectedQuantity:" + selectedQuantity;
    }
}
