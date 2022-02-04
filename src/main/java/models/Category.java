package models;

public class Category {


    public Category(String name, Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Category(String name,int productID) {
        this.id = id;
        this.name = name;
    }

    public Category(String name,int productID,Category parentCategory) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    private int id;
    private String name;
    private Category parentCategory;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setParentCategoryId(int id){
        this.parentCategory.setId(id);
    }

    public int getParentCategoryID(){
        return this.parentCategory.getId();
    }


    @Override
    public String toString() {
        return "\nid: " + id +
                "\nname: " + name;
    }
}
