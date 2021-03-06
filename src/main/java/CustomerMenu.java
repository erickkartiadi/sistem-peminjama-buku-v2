import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class CustomerMenu {
    static SimpleDateFormat dateInput = new SimpleDateFormat("dd/MM/yyyy");
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Customer> customerArrayList = new ArrayList<>();

    public static void showCustomerMenu() {
        int choose;

        do {
            System.out.println("1. Add New User");
            System.out.println("2. Show User");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("5. Back To Menu");
            System.out.print(">>");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    GlobalFunction.cls();
                    addNewUser();
                    break;
                case 2:
                    GlobalFunction.cls();
                    userList();
                    GlobalFunction.showMessage("");
                    break;
                case 3:
                    GlobalFunction.cls();
                    editUser();
                    break;
                case 4:
                    GlobalFunction.cls();
                    deleteUser();
                    break;

            }
            GlobalFunction.cls();
        } while (choose != 5);
    }

    public static boolean validateEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public static void addNewUser() {
        String username;
        String email;
        Date DOB;
        String phoneNumber;

        username = inputUsername();
        email = inputEmail();
        DOB = inputDOB();
        phoneNumber = inputPhoneNumber();

        Date joinedDate = new Date();
        Customer newCustomer = new Customer(username, email, DOB, phoneNumber, joinedDate, 3, 1);
        customerArrayList.add(newCustomer);

        System.out.println();
        System.out.println("Customer has been successfully inputed");
        GlobalFunction.pressContinue();
    }

    public static String inputPhoneNumber() {
        String phoneNumber;
        do {
            System.out.print("Input phone number [11-13]: ");
            phoneNumber = scan.nextLine();

            boolean isLengthValid = phoneNumber.length() >= 11 && phoneNumber.length() <= 13;
            boolean isOnlyNumbers = phoneNumber.chars().allMatch(Character::isDigit);

            if (isLengthValid && isOnlyNumbers) {
                break;
            } else {
                System.out.println("Phone number must between 11-13 and only contain numbers");
            }
        } while (true);
        return phoneNumber;
    }

    public static Date inputDOB() {
        Date DOB;
        do {
            System.out.print("Input date of birth [dd/MM/yyyy]: ");
            String strDOB = scan.nextLine();

            try {
                DOB = dateInput.parse(strDOB);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        } while (true);
        return DOB;
    }

    public static String inputEmail() {
        String email;
        do {
            System.out.print("Input email: ");
            email = scan.nextLine();

            //TODO check if email is already registered
            if (validateEmail(email)) {
                break;
            } else {
                System.out.println("Email is invalid");
            }
        } while (true);
        return email;
    }

    public static String inputUsername() {
        String username;
        do {
            System.out.print("Input username [5..20]: ");
            username = scan.nextLine();

            int len = username.length();
            //TODO check if username is already used
            if (len >= 5 && len <= 20) {
                break;
            } else {
                System.out.println("Username must be between 5 - 20 length");
            }
        } while (true);
        return username;
    }

    public static void userList(){
        showUser();
        GlobalFunction.pressContinue();
    }

    public static void showUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
            System.out.println();
            System.out.println("Customer list is empty");
            return;
        }

        System.out.println("+----+" + "------------------------------------+" + "------------------------+" + "------------------------------------+" + "------------+" + "---------------+" + "------------+");
        System.out.println("|No. |               User ID              |" + "        Username        " + "|                Email               |" + "    DOB     |" + "  Phone Number |" + "  Join Date |");
        System.out.println("+----+" + "------------------------------------+" + "------------------------+" + "------------------------------------+" + "------------+" + "---------------+" + "------------+");
        int number = 0;
        for (Customer currentCustomer : customerArrayList) {
            number++;

            String id = currentCustomer.getCustomerID();
            String username = currentCustomer.getUsername();
            String email = currentCustomer.getEmail();
            String DOB = dateInput.format(currentCustomer.getDOB());
            String phoneNumber = currentCustomer.getPhoneNumber();
            String joinedDate = dateInput.format(currentCustomer.getJoinedDate());

            System.out.printf("|%-4s|%-36s|%-24s|%-36s|%-12s|%-15s|%-12s|\n", number, id, username, email, DOB, phoneNumber, joinedDate);
        }
        System.out.println("+----+" + "------------------------------------+" + "------------------------+" + "------------------------------------+" + "------------+" + "---------------+" + "------------+");
    }

    public static void editUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
            System.out.println();
            System.out.println("Customer list is empty");
            GlobalFunction.pressContinue();
            return;
        }

        showUser();
        int number;
        do {
            System.out.print("Input number to edit [1.." + customerArrayListLen + "]:");
            number = scan.nextInt();
            scan.nextLine();

        } while (number > customerArrayListLen || number < 0);

        Customer currentCustomer = customerArrayList.get(number - 1);

        int choose;
        do {
            System.out.println("Which one to edit");
            System.out.println("1. Username");
            System.out.println("2. Email");
            System.out.println("3. Phone Number");
            System.out.println("4. DOB");
            System.out.println("5. Cancel Edit");
            System.out.print(">>");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    String username = inputUsername();
                    currentCustomer.setUsername(username);
                    System.out.println();
                    System.out.println("Customer has been successfully edited");
                    GlobalFunction.pressContinue();
                    break;
                case 2:
                    String email = inputEmail();
                    currentCustomer.setEmail(email);
                    System.out.println();
                    System.out.println("Customer has been successfully edited");
                    GlobalFunction.pressContinue();
                    break;
                case 3:
                    String phoneNumber = inputPhoneNumber();
                    currentCustomer.setPhoneNumber(phoneNumber);
                    System.out.println();
                    System.out.println("Customer has been successfully edited");
                    GlobalFunction.pressContinue();
                    break;
                case 4:
                    Date DOB = inputDOB();
                    currentCustomer.setDOB(DOB);
                    System.out.println();
                    System.out.println("Customer has been successfully edited");
                    GlobalFunction.pressContinue();
                    break;
            }
        } while (choose != 5);
    }

    public static void deleteUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
            System.out.println();
            System.out.println("Customer list is empty");
            GlobalFunction.pressContinue();
            return;
        }

        showUser();
        int number;
        do {
            System.out.print("Input number to delete [1.." + customerArrayListLen + "]:");
            number = scan.nextInt();
            scan.nextLine();

            System.out.println();
            System.out.println("Customer has been successfully deleted");
            GlobalFunction.pressContinue();

        } while (number > customerArrayListLen || number < 0);
        customerArrayList.remove(number - 1);
    }
}
