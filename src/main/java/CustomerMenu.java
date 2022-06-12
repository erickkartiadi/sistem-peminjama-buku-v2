import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerMenu {
    SimpleDateFormat dateInput = new SimpleDateFormat("dd/MM/yyyy");
    Scanner scan = new Scanner(System.in);
    ArrayList<Customer> customerArrayList = new ArrayList<>();

    public CustomerMenu() {
        int choose;

        do {
            System.out.println("1. Add New User");
            System.out.println("2. Show User");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print(">>");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    addNewUser();
                    break;
                case 2:
                    showUser();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
                    break;

            }
        } while (choose != 5);
    }

    public static void main(String[] args) {
        new CustomerMenu();
    }

    public static boolean validateEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    private void addNewUser() {
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
    }

    private String inputPhoneNumber() {
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

    private Date inputDOB() {
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

    private String inputEmail() {
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

    private String inputUsername() {
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

    private void showUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
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

    private void editUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
            System.out.println("Customer list is empty");
            return;
        }


        showUser();
        int number = 0;
        do {
            System.out.print("Input number to edit [1.." + customerArrayListLen + "]:");
            number = scan.nextInt();
            scan.nextLine();

            if (number <= customerArrayListLen && number >= 0) {
                break;
            }

        } while (true);

        Customer currentCustomer = customerArrayList.get(number - 1);

        int choose;
        do {
            System.out.println("Which one to edit");
            System.out.println("1. Username");
            System.out.println("2. Email");
            System.out.println("3. Phone Number");
            System.out.println("4. DOB");
            System.out.println("5. Exit");
            System.out.print(">>");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    String username = inputUsername();
                    currentCustomer.setUsername(username);
                    break;
                case 2:
                    String email = inputEmail();
                    currentCustomer.setEmail(email);
                    break;
                case 3:
                    String phoneNumber = inputPhoneNumber();
                    currentCustomer.setPhoneNumber(phoneNumber);
                    break;
                case 4:
                    Date DOB = inputDOB();
                    currentCustomer.setDOB(DOB);
                    break;
            }
        } while (choose != 5);
    }

    private void deleteUser() {
        int customerArrayListLen = customerArrayList.size();
        if (customerArrayListLen <= 0) {
            System.out.println("Customer list is empty");
            return;
        }

        showUser();
        int number = 0;
        do {
            System.out.print("Input number to delete [1.." + customerArrayListLen + "]:");
            number = scan.nextInt();
            scan.nextLine();

            if (number <= customerArrayListLen && number >= 0) {
                break;
            }

        } while (true);
        customerArrayList.remove(number - 1);
    }


}
