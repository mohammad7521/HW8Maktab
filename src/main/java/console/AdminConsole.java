package console;

import models.Category;
import models.Product;
import services.ProductServices;
import services.categories.ChildCategoryServices;
import services.categories.ParentCategoryServices;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AdminConsole {

public static ProductServices productServices=new ProductServices();
public static ChildCategoryServices childCategoryServices=new ChildCategoryServices();
public static ParentCategoryServices parentCategoryServices=new ParentCategoryServices();
    public static void adminMenu()  {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-product management");
            System.out.println("2-category management");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        productManagement();
                        break;

                    case 2:
                        categoryManagement();
                        break;

                    case 0:
                        flag = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number ! ");
            }
        }
    }



    public static void productManagement() {

        ProductServices productServices=new ProductServices();
        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add new product");
            System.out.println("2-recharge a product");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("enter new product name:");
                        String productName = scanner.next();
                        System.out.println("enter price:");
                        double price = scanner.nextDouble();
                        System.out.println("enter quantity:");
                        int quantity=scanner.nextInt();

                        //selecting the category
                        List<Category> categoryList= ParentCategoryServices.showAll();

                        if (categoryList.size()<1){
                            System.out.println("no categories defined! please define categories from the menu");
                            break;
                        }
                        else
                        System.out.println("select the category by id:");

                        //select the parent category for product

                        for (Category c:categoryList){
                            System.out.println(c.toString());
                        }
                        int parentCategoryID=scanner.nextInt();
                        //select the child category

                        List<Category> childCategoryList=childCategoryServices.showCategoriesOfParent(parentCategoryID);
                        if (childCategoryList.size()<1){
                            System.out.println("no child category has been defined! please define from the menu!");
                            break;
                        }
                        else
                        for (Category c:childCategoryList) {
                            System.out.println(c.toString());
                        }
                        System.out.println();
                        System.out.println("select the child category by id:");
                        int childCategoryID=scanner.nextInt();
                        Category category=ChildCategoryServices.showInfo(childCategoryID);

                        //assigning the category to product
                        Product product=new Product(productName,category,price,quantity);

                        //adding product to the table
                        int productID=productServices.add(product);
                        if (productID>0 && ChildCategoryServices.addProduct(productID,product.getCategory().getId())) {
                            System.out.println("product added successfully");
                        }
                        break;


                    case 2:
                            System.out.println("enter the desired product ID");
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("enter added quantity:");
                            quantity = scanner.nextInt();
                            if (ProductServices.recharge(productID,quantity)) {
                                System.out.println("product has been recharged successfully! ");
                            } else System.out.println("product ID does not exist! ");
                            break;
                    case 0:
                        flag = false;
                        break;

                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number !");
            }
        }
    }



    public static void categoryManagement() {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-Add new category");
            System.out.println("2-modify a category");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userSelect = scanner.nextInt();
                scanner.nextLine();
                switch (userSelect) {

                    case 1:
                        System.out.println("is it a main category?(enter y for yes or any other for no)");
                        char answer=scanner.next().charAt(0);
                        if (answer=='y' || answer=='Y'){

                            System.out.println("enter name:");
                            String categoryName=scanner.next();
                            Category category=new Category();
                            category.setName(categoryName);;
                            if (parentCategoryServices.add(category)>0){
                                System.out.println("category created successfully");
                            }

                        }else {
                            System.out.println("select the parent category: ");
                            List<Category> categoryList=ParentCategoryServices.showAll();
                            for (Category category:categoryList){
                                System.out.println(category.toString());
                            }
                            scanner.nextLine();
                            int parentCategoryID=scanner.nextInt();
                            System.out.println("enter name: ");
                            Category category=new Category();
                            category.setParentCategory(ParentCategoryServices.showInfo(parentCategoryID));
                            category.setParentCategoryId(parentCategoryID);
                            category.setName(scanner.next());
                            if (childCategoryServices.add(category)>0){
                                System.out.println("category has been added successfully!");
                            }
                        }

                        break;

                    case 0:
                        flag = false;
                        break;

                }
            }catch (InputMismatchException e){
                System.out.println("please enter a valid number !");
            }
        }
    }
}
