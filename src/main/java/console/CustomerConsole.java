package console;//package console;
//
//
//import java.sql.Date;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class CustomerConsole {
//
//    public static void customerLogInMenu() {
//
//        boolean flag = true;
//        while (flag) {
//            System.out.println("1-Register: ");
//            System.out.println("2-Sign in: ");
//            System.out.println("0-exit");
//
//            Scanner scanner = new Scanner(System.in);
//
//            try {
//                int userEntry = scanner.nextInt();
//                switch (userEntry) {
//                    case 1:
//                        System.out.println("enter a username: ");
//                        String username=scanner.next();
//                        System.out.println("enter a password: ");
//                        String password=scanner.next();
//                        System.out.println("enter your address: ");
//                        String firstName=scanner.next();
//                        System.out.println("enter your phone number: ");
//                        String phoneNumber=scanner.next();
//                        System.out.println("enter your national code:");
//                        String nationalCode=scanner.next();
//                        String lastName=scanner.next();
//                        try {
//                            ViewerService.addNew(username, password, firstName, lastName);
//                        }catch (DuplicateUser e){
//                            System.out.println("username already exists! ");
//                        }
//                        break;
//                    case 2:
//                        System.out.println("please enter your username: ");
//                        username=scanner.next();
//                        System.out.println("please enter your password: ");
//                        password=scanner.next();
//                        try {
//                            if (ViewerService.logIn(username, password)) {
//                                System.out.println("log in successful! ");
//                                viewerMainMenu(username);
//                                break;
//                            } else System.out.println("password is wrong! ");
//                            break;
//                        }catch (NullPointerException e){
//                            System.out.println("username does not exist! ");
//                        }
//                    case 0:
//                        flag = false;
//                        break;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("please enter a valid number ! ");
//            }
//        }
//    }
//    public static void clientMenu() {
//
//
//        try {
//
//            boolean flag = true;
//            System.out.println("enter your ID");
//            Scanner scanner = new Scanner(System.in);
//            int id = scanner.nextInt();
//            Client client = ClientService.showInfo(id);
//            if (client.getId()!=id){
//                flag=false;
//                System.out.println("this id does not exist! ");
//            }
//
//            while (flag) {
//
//                System.out.println();
//                System.out.println("1-Show accounts list");
//                System.out.println("2-create a transaction");
//                System.out.println("3-show All transactions");
//                System.out.println("4-show transaction based on specific date");
//                System.out.println("5-Change password");
//                System.out.println("6-add new account");
//                System.out.println("7-deposit");
//                System.out.println("8-withdraw");
//                System.out.println("0-exit");
//
//                int userEntry = scanner.nextInt();
//                switch (userEntry) {
//                    case 1:
//                        ClientService.showAccountList(id);
//                        break;
//                    case 2:
//                        System.out.println("select your account by ID");
//                        ClientService.showAccountList(id);
//                        int accountID = scanner.nextInt();
//                        System.out.println("enter amount: ");
//                        long amount = scanner.nextLong();
//                        System.out.println("enter destination ccNumber");
//                        long destinationCC = scanner.nextLong();
//                        try {
//
//                            CreditCard creditCard = CreditCardService.showInfo(destinationCC);
//                            if (creditCard.getCcNumber() != destinationCC) {
//                                System.out.println("destination credit card number wrong!");
//                                break;
//                            }
//                        }catch (NullPointerException e){
//                            System.out.println("credit card number does not exist");
//                            break;
//                        }
//                        System.out.println("enter your cvv2");
//                        int cvv2 = scanner.nextInt();
//                        System.out.println("enter your password");
//                        int password = scanner.nextInt();
//                        System.out.println("enter description");
//                        scanner.nextLine();
//                        String description = scanner.next();
//                        ClientService.createTransaction(accountID, destinationCC, amount, description
//                                , cvv2, password);
//                        break;
//                    case 3:
//                        ClientService.showAccountList(id);
//                        System.out.println("select the account");
//                        accountID = scanner.nextInt();
//                        ClientService.showTransactionList(accountID);
//                        break;
//                    case 4:
//                        try {
//                            ClientService.showAccountList(id);
//                            System.out.println("enter starting date in the following format");
//                            System.out.println("yyyy-mm-dd");
//                            Date startDate = Date.valueOf(scanner.next());
//                            System.out.println("enter the account id");
//                            accountID = scanner.nextInt();
//                            ClientService.showTransactionList(accountID, startDate);
//                            break;
//                        }catch (IllegalArgumentException e){
//                            System.out.println("please enter the date in the correct format");
//                            break;
//                        }
//                    case 5:
//                        ClientService.showAccountList(id);
//                        System.out.println("enter the account id");
//                        accountID = scanner.nextInt();
//                        System.out.println("enter the old password");
//                        int oldPass = scanner.nextInt();
//                        System.out.println("enter the new password");
//                        int newPass = scanner.nextInt();
//                        ClientService.changeCCPassword(accountID, oldPass, newPass);
//                        break;
//                    case 6:
//                        System.out.println("enter your branch:");
//                        int branchID = scanner.nextInt();
//                        System.out.println("enter your initial deposit");
//                        long initialDeposit = scanner.nextInt();
//                        ClientService.createAccount(id, branchID, initialDeposit);
//                        break;
//                    case 7:
//                        System.out.println("enter the amount:");
//                        amount = scanner.nextLong();
//                        System.out.println("enter the account id:");
//                        accountID = scanner.nextInt();
//                        ClientService.deposit(accountID, amount);
//                        break;
//                    case 8:
//                        System.out.println("enter the amount:");
//                        amount = scanner.nextLong();
//                        System.out.println("enter account id:");
//                        ClientService.showAccountList(id);
//                        accountID = scanner.nextInt();
//                        System.out.println("enter your password");
//                        password = scanner.nextInt();
//                        ClientService.withDraw(accountID, amount, password);
//                        break;
//                    case 0:
//                        flag = false;
//                        break;
//                }
//            }
//        }catch(InputMismatchException e ){
//            System.out.println("please enter a valid number ! ");
//        }
//    }
//}
