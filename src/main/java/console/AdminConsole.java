package console;

import models.Category;
import models.Product;
import services.ProductServices;
import services.categories.ChildCategoryServices;
import services.categories.ParentCategoryServices;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminConsole {


    public static void adminMenu()  {

        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1-product management");
            System.out.println("2-user management");
            System.out.println("0-exit");

            Scanner scanner = new Scanner(System.in);

            try {
                int userEntry = scanner.nextInt();
                switch (userEntry) {
                    case 1:
                        productManagement();
                        break;

//                    case 2:
//                        userManagement();
//                        break;

//                    case 3:
//                        categoryManagement();
//                        break;

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
            System.out.println("2-remove a product");
            System.out.println("3-modify a product");
            System.out.println("4-recharge a product");
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
                        System.out.println("select the category by id:");

                        //select the parent category for product
                        List<Category> categoryList= ParentCategoryServices.showAll();

                        for (Category c:categoryList){
                            System.out.println(c.toString());
                        }
                        int parentCategoryID=scanner.nextInt();
                        //select the child category

                        List<Category> childCategoryList=ChildCategoryServices.showCategoriesOfParent(parentCategoryID);
                        for (Category c:childCategoryList) {
                            System.out.println(c.toString());
                        }
                        System.out.println("select the child category by id:");
                        int childCategoryID=scanner.nextInt();
                        Category category=ChildCategoryServices.showInfo(childCategoryID);

                        //assigning the category to product
                        Product product=new Product(productName,category,price,quantity);

                        //adding product to the table
                        int productID=ProductServices.add(product);
                        if (productID>0 && ChildCategoryServices.addProduct(productID,product.getCategory().getId())) {
                            System.out.println("product added successfully");
                        }
                        break;

                    case 2:
                        System.out.println("enter the desired product ID");
                        productID = scanner.nextInt();
                        if (productServices.delete(productID)) {
                            System.out.println("product has been deleted successfully! ");
                            break;
                        } else System.out.println("product ID does not exist! ");
                        break;

                    case 3:
                            System.out.println("enter the desired product ID");
                            productID = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("enter new name:");
                            String newName = scanner.nextLine();
                            System.out.println("enter new price:");
                            int newPrice=scanner.nextInt();
                            System.out.println("enter new quantity:");
                            quantity=scanner.nextInt();
                            if (productServices.modify(productID,newName,newPrice,quantity)) {
                                System.out.println("product has been modified successfully! ");
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






//
//    public static void userManagement() {
//
//        boolean flag = true;
//        while (flag) {
//            System.out.println();
//            System.out.println("1-Add new user");
//            System.out.println("2-remove a user");
//            System.out.println("3-modify a branch Boss");
//            System.out.println("0-exit");
//
//            Scanner scanner = new Scanner(System.in);
//
//            try {
//                int userSelect = scanner.nextInt();
//                scanner.nextLine();
//                switch (userSelect) {
//
//                    case 1:
//                        System.out.println("enter first name:");
//                        String firstName = scanner.next();
//                        System.out.println("enter last name:");
//                        String lastName = scanner.next();
//                        System.out.println("enter address:");
//                        String address = scanner.next();
//                        System.out.println("enter working branch ID:");
//                        int branchId = scanner.nextInt();
//                        scanner.nextLine();
//                        BossService.addNew(firstName, lastName, address, branchId);
//                        break;
//
//                    case 2:
//                        System.out.println("enter the desired branch boss ID");
//                        int bossID = scanner.nextInt();
//                        if (BossService.remove(bossID)) {
//                            System.out.println("branch Boss removed successfully! ");
//                            break;
//                        } else System.out.println("branch boss ID does not exist! ");
//                        break;
//                    case 3:
//
//                        System.out.println("enter the desired branch boss ID");
//                        int ID = scanner.nextInt();
//                        scanner.nextLine();
//                        System.out.println("enter new first name:");
//                        String newName = scanner.nextLine();
//                        System.out.println("enter new last name:");
//                        String newLastName = scanner.next();
//                        System.out.println("enter new address:");
//                        String newAddress = scanner.next();
//                        BossService.modify(ID, newName, newLastName, newAddress);
//
//                        break;
//
//                    case 0:
//                        flag = false;
//                        break;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("please enter a valid number ! ");
//            }
//        }
//    }

//
//    public static void categoryManagement() {
//
//        boolean flag=true;
//        while (flag) {
//            System.out.println();
//            System.out.println("1-Add new category");
//            System.out.println("2-remove a category");
//            System.out.println("3-modify a modify");
//            System.out.println("0-exit");
//
//            Scanner scanner = new Scanner(System.in);
//
//            try {
//                int userSelect = scanner.nextInt();
//                scanner.nextLine();
//                switch (userSelect) {
//
//                    case 1:
//                        System.out.println("enter new category name");
//                        String categoryName = scanner.next();
//                        System.out.println("select the parent category by id:");
//                        List<Category> categoryList=CategoryServices.showAll();
//
//                        for (Category category:categoryList){
//                            System.out.println(category);
//                        }
//                        int parentCategoryID=scanner.nextInt();
//                        Category category=new Category(categoryName,CategoryServices.showInfo(parentCategoryID));
//
//                        if (CategoryServices.add(category)) {
//                            System.out.println("category added successfully! ");
//                        }
//                        break;
//
//                    case 2:
//                        while (true) {
//                            System.out.println("enter the desired category ID");
//                            int branchID = scanner.nextInt();
//                            if (BranchService.remove(branchID)) {
//                                System.out.println("branch has been removed successfully! ");
//                                break;
//                            } else System.out.println("branch ID does not exist! ");
//                            break;
//                        }
//                        break;
//
//                    case 3:
//                        while (true) {
//                            System.out.println("enter the desired branch ID");
//                            int branchID = scanner.nextInt();
//                            scanner.nextLine();
//                            System.out.println("enter new name:");
//                            String newName = scanner.nextLine();
//                            System.out.println("enter new address:");
//                            String branchAddress = scanner.next();
//
//                            if (BranchService.modify(branchID, newName, branchAddress)) {
//                                System.out.println("branch has been modified successfully! ");
//                            } else System.out.println("branch ID does not exist! ");
//                            break;
//                        }
//                    case 0:
//                        flag = false;
//                        break;
//
//                }
//            }catch (InputMismatchException e){
//                System.out.println("please enter a valid number !");
//            }
//        }
//    }
}
