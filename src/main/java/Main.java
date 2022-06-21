import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Vector<BorrowedBook> borrowedBook = new Vector<>();
    static Vector<Transaction> transactionList = new Vector<>();

    public static void main(String[] args) {
        int choose;

        do {
            title();
            System.out.println("1. Manage Book");
            System.out.println("2. Borrow book");
            System.out.println("3. Return book");
            System.out.println("4. Show On Going Transaction");
            System.out.println("5. Show History");
            System.out.println("6. Manage User");
            System.out.println("7. Customer List");
            System.out.println("8. Borrowed Book List");
            System.out.println("9. Exit");
            System.out.print("Choose >> ");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    GlobalFunction.cls();
                    manageBook();
                    break;
                case 2:
                    GlobalFunction.cls();
                    borrowBook();
                    break;
                case 3:
                    GlobalFunction.cls();
                    returnBook();
                    break;
                case 4:
                    GlobalFunction.cls();
                    showTransaction();
                    break;
                case 5:
                    GlobalFunction.cls();
                    showHistory();
                    break;
                case 6:
                    GlobalFunction.cls();
                    manageUser();
                    break;
                case 7:
                    GlobalFunction.cls();
                    customerList();
                    GlobalFunction.showMessage("");
                    break;
                case 8:
                    GlobalFunction.cls();
                    borrowedBookList();
                    GlobalFunction.showMessage("");
                    break;

            }
            GlobalFunction.cls();
        } while (choose != 9);
    }

    private static void manageUser() {
        CustomerMenu.showCustomerMenu();
    }

    private static void manageBook(){
        BookMenu.bookMenu();
    }

    private static void title(){
        System.out.println("   _____ _     _                   _____               _       _                               ____        _          ");
        System.out.println("  / ____(_)   | |                 |  __ \\             (_)     (_)                             |  _ \\      | |         ");
        System.out.println(" | (___  _ ___| |_ ___ _ __ ___   | |__) |__ _ __ ___  _ _ __  _  __ _ _ __ ___   __ _ _ __   | |_) |_   _| | ___   _ ");                                                   
        System.out.println("  \\___ \\| / __| __/ _ \\ '_ ` _ \\  |  ___/ _ \\ '_ ` _ \\| | '_ \\| |/ _` | '_ ` _ \\ / _` | '_ \\  |  _ <| | | | |/ / | | |");
        System.out.println("  ____) | \\__ \\ ||  __/ | | | | | | |  |  __/ | | | | | | | | | | (_| | | | | | | (_| | | | | | |_) | |_| |   <| |_| |");
        System.out.println(" |_____/|_|___/\\__\\___|_| |_| |_| |_|   \\___|_| |_| |_|_|_| |_| |\\__,_|_| |_| |_|\\__,_|_| |_| |____/ \\__,_|_|\\_\\\\__,_|");
        System.out.println("                                                             _/ |                                                     ");
        System.out.println("                                                            |__/                                                      ");
    }

    private static int searchBook(String id) {
        for (int i = 0; i < BookMenu.books.size(); i++) {
           
            Book data = BookMenu.books.get(i);
            if (data.getBookId().toString().equals(id)) {
                return i;
            }
        }

        return -1;
    }

    private static int searchTransaction(String id) {
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction data = transactionList.get(i);

            if (data.getTransactionId().toString().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static int searchCustomer(String id) {
        for (int i = 0; i < CustomerMenu.customerArrayList.size(); i++) {
            User data = CustomerMenu.customerArrayList.get(i);
            if (((Customer)data).getCustomerID().toString().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static void borrowBook() {
        if (CustomerMenu.customerArrayList.isEmpty()) {
            System.out.println();
            System.out.println("Cannot borrow book because there is no customer");
            GlobalFunction.pressContinue();
        }
        else
        {
            String custId;
            customerList();
            do {
                System.out.println("Enter Customer ID :");
                custId = scan.nextLine();
            } while (searchCustomer(custId) == -1);

            if(!BookMenu.getAvailability()){
                System.out.println();
                System.out.println("Cannot borrow book because there is no book available");
                GlobalFunction.pressContinue();
                return;
            }
            BookMenu.viewBooks();
            Integer inp;
            String id;
            String b;
            String addBook;
            Integer removedBook;
            int num = 0;
            int[] idx;
            idx = new int[5];

            do {
                System.out.println("Input Book ID to be borrowed: ");
                id = scan.nextLine();
               
                if (searchBook(id) != -1) {
                    idx[num] = searchBook(id);
                    num++;
                    System.out.println("Borrow another book? [Y | N]");
                    b = scan.nextLine();
                    if (b.equals("N")) {
                        break;
                    }

                } else {
                    System.out.println("msk");
                    System.out.println("Book ID is invalid, please re-enter the correct Book ID");
                    System.out.println("Press enter to continue");
                    scan.nextLine();

                    System.out.println();
                }
            } while (true);

            do {
                System.out.println();
                System.out.println("The borrowed book list: ");

                for (int i = 0; i < num; i++) {
                    Book data = BookMenu.books.get(idx[i]);
                    System.out.println("ID: " + data.getBookId());
                    System.out.println("Title: " + data.getBookName());
                    System.out.println("Edition: " + data.getBookEdition());
                    System.out.println();
                }

                do {
                    System.out.println("Are you sure to borrow those list of books?");
                    System.out.println("1. Confirm");
                    System.out.println("2. Remove book");
                    System.out.println("3. Add book");
                    System.out.println("4. Cancel");
                    inp = scan.nextInt();
                    scan.nextLine();

                } while (inp < 1 || inp > 4);

                // EDIT HERE - ANDRE
                if (inp == 1) {
                    break;
                } else if (inp == 2) {
                    for (int i = 0; i < num; i++) {
                        Book data = BookMenu.books.get(idx[i]);
                        System.out.println("No. " + (i + 1));
                        System.out.println("ID: " + data.getBookId());
                        System.out.println("Title: " + data.getBookName());
                        System.out.println("Edition: " + data.getBookEdition());
                        System.out.println();
                    }
                    do {
                        System.out.println("Input No. from the list to be removed: ");
                        removedBook = scan.nextInt();
                        scan.nextLine();
                    } while (removedBook > idx.length);

                    if (num == 1) {
                        num = 0;
                    } else {
                        for (int j = removedBook; j < idx.length; j++) {
                            idx[j - 1] = idx[j];
                        }
                        num = num - 1;
                    }
                } else if (inp == 3) {

                    do {
                        System.out.println("Input Book ID to be borrowed: ");
                        addBook = scan.nextLine();

                        if (searchBook(addBook) != -1) {
                            idx[num] = searchBook(addBook);
                            num++;
                            System.out.println("Borrow another book? [Y | N]");
                            b = scan.nextLine();
                            if (b.equals("N")) {
                                break;
                            }
                        } else if (searchBook(addBook) == -1) {
                            System.out.println("Book ID is invalid, please re-enter the correct Book ID");
                            System.out.println("Press enter to continue");
                            scan.nextLine();

                            System.out.println();
                        }

                    } while (true);
                } else if (inp == 4) {
                    System.out.println();
                    return;
                }
            } while (true);

            if (num != 0) {
                LocalDate localDate = LocalDate.now();

                String tranId = "TR";
                int min = 0;
                int max = 9;

                Random random = new Random();

                int num1 = random.nextInt((max - min) + 1) + min;
                int num2 = random.nextInt((max - min) + 1) + min;
                int num3 = random.nextInt((max - min) + 1) + min;
                tranId = tranId + num1 + num2 + num3;

                System.out.println("The borrowed book list: ");
                for (int i = 0; i < num; i++) {
                    Book data = BookMenu.books.get(idx[i]);
                    System.out.println("ID: " + data.getBookId());
                    System.out.println("Title: " + data.getBookName());
                    System.out.println("Edition: " + data.getBookEdition());
                    System.out.println();

                    borrowedBook.add(new BorrowedBook(data.getBookId(), data.getBookName(), data.getBookEdition()));
                    Integer bookIndex = searchBook(data.getBookId().toString());
                    Book temp = BookMenu.books.get(bookIndex);
                    BookMenu.books.set(bookIndex, new Book(temp.getBookId(), temp.getBookName(), temp.getBookEdition(), temp.getBookQuantity() - 1));
                }
                // TODO ganti customer_id
                transactionList.add(new Transaction(localDate, custId, tranId, false, borrowedBook));

                System.out.println("Transaction has been added successfully with ID : "+tranId);
                System.out.println("");
            } else {
                System.out.println("You don't have book on the borrowed list");
                System.out.println("Press enter to continue");
                scan.nextLine();

                System.out.println("");
            }

            num = 0;
        }
    }

    private static void showTransaction() {
        int transactionIndex = -1;
        String id;
        boolean isExist = false;

        for (int i = 0; i < transactionList.size(); i++) {

            Transaction data = transactionList.get(i);
            if(data.getIsReturned() == true){
                continue;
            } else {
                System.out.println("No." + (i + 1));
                System.out.println("Transaction Id: " + data.getTransactionId());
                System.out.println("Customer Id: " + data.getcustomerId());
                System.out.println("");
                isExist = true;
            }
            
        }

        if(isExist == true){
            do {
                System.out.println("Choose Id of transaction to view borrowed book list");
                id = scan.nextLine();
                transactionIndex = searchTransaction(id);
            } while (transactionIndex == -1);
        } else {
            System.out.println();
            System.out.println("There is no on going transaction");
            GlobalFunction.pressContinue();
            return;
        }

        System.out.println("");

        Vector<BorrowedBook> borrow = transactionList.get(transactionIndex).getBorrowedBookList();
        System.out.println("List of borrowed book: ");
        for (int j = 0; j < borrow.size(); j++) {
            System.out.println((j + 1) + ". ");
            System.out.println("BookId: " + borrow.get(j).getBookId());
            System.out.println("Title: " + borrow.get(j).getBookName());
            System.out.println("Edition: " + borrow.get(j).getBookEdition());
            System.out.println("");
        }
        GlobalFunction.pressContinue();
    }

    private static void showHistory() {
        int transactionIndex = -1;
        String id;
        boolean isExist = false;
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction data = transactionList.get(i);
            if(data.getIsReturned() == false){
                continue;
            } else {
                System.out.println("No." + (i + 1));
                System.out.println("Transaction Id: " + data.getTransactionId());
                System.out.println("Customer Id: " + data.getcustomerId());
                System.out.println("");
                isExist = true;
            }
        }
        if(isExist == true){
            do {
                System.out.println("Choose Id of transaction to view borrowed book list");
                id = scan.nextLine();
                transactionIndex = searchTransaction(id);
            } while (transactionIndex == -1);
        } else {
            System.out.println();
            System.out.println("History is empty");
            GlobalFunction.pressContinue();
            return;
        }

        System.out.println("");

        Vector<BorrowedBook> borrow = transactionList.get(transactionIndex).getBorrowedBookList();
        System.out.println("List of borrowed book: ");
        for (int j = 0; j < borrow.size(); j++) {
            System.out.println((j + 1) + ". ");
            System.out.println("BookId: " + borrow.get(j).getBookId());
            System.out.println("Title: " + borrow.get(j).getBookName());
            System.out.println("Edition: " + borrow.get(j).getBookEdition());
            System.out.println("");
        }
        GlobalFunction.pressContinue();
    }

    // private static void removeBook() {
    //     String bookId;
    //     viewBooks();
    //     System.out.print("Enter the book id you want to delete: ");
    //     bookId = scan.nextLine();

    //     books.remove(searchBook(bookId));
    // }

    // private static void editBook() {
    //     String bookId;
    //     viewBooks();
    //     System.out.print("Enter the book id you want to edit: ");
    //     bookId = scan.nextLine();

    //     Book book = books.get(searchBook(bookId));

    //     int choose;
    //     do {
    //         System.out.println("Which attribute you want to edit");
    //         System.out.println("1. Book name");
    //         System.out.println("2. Book edition");
    //         System.out.println("3. Book quantity");
    //         System.out.println("4. Cancel edit");
    //         System.out.print("Choose >> ");
    //         choose = Integer.parseInt(scan.nextLine());

    //         switch (choose) {
    //             case 1:
    //                 System.out.print("Enter new book name: ");
    //                 book.setBookName(scan.nextLine());
    //                 break;
    //             case 2:
    //                 System.out.print("Enter new boo k edition: ");
    //                 book.setBookEdition(scan.nextLine());
    //                 break;
    //             case 3:
    //                 System.out.print("Enter new book quantity: ");
    //                 book.setBookQuantity(Integer.parseInt(scan.nextLine()));
    //                 break;
    //         }

    //     } while (choose != 4);
    // }

    // private static void viewBooks() {
    //     int number = 0;
    //     System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
    //     System.out.println("| No. |Book Id                             | Book Name          | Book Edition       |Book Quantity  |");
    //     System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
    //     for (Book currentBook : books) {
    //         int bookQuantity = currentBook.getBookQuantity();

    //         if (bookQuantity > 0) {
    //             number++;
    //             System.out.printf("|%-5s|%-36s|%-20s|%-20s|%-15s|%n", number, currentBook.getBookId(), currentBook.getBookName(), currentBook.getBookEdition(), bookQuantity);
    //         }
    //     }
    //     System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");

    // }

    // static public void addBook() {
    //     UUID bookId = UUID.randomUUID();
    //     System.out.print("Enter book name: ");
    //     String bookName = scan.nextLine();
    //     System.out.print("Enter book edition: ");
    //     String bookEdition = scan.nextLine();
    //     System.out.print("Enter book quantity: ");
    //     Integer bookQuantity = Integer.parseInt(scan.nextLine());

    //     Book book = new Book(bookId, bookName, bookEdition, bookQuantity);
    //     books.add(book);
    // }

    private static void returnBook() {
        String transactionID;
        Integer transactionIndex;

        while (true) {
            System.out.print("Please input the transaction id [Please input space to cancel]: ");
            transactionID = scan.nextLine();

            if (transactionID.equals(" ")) {
                return;
            }

            transactionIndex = searchTransaction(transactionID);

            if (transactionIndex == -1) {
                System.out.println("The transaction ID is invalid.");
                System.out.println("Press enter to continue...");
                scan.nextLine();
                continue;
            }

            break;
        }

        Transaction data = transactionList.get(transactionIndex);
        System.out.println("Transaction Id: " + data.getTransactionId());
        System.out.println("Customer Id: " + data.getcustomerId());
        System.out.println("Book details: ");
        System.out.println();
        Vector<BorrowedBook> borrow = transactionList.get(transactionIndex).getBorrowedBookList();
        System.out.println("List of borrowed book: ");
        for (int i = 0; i < borrow.size(); i++) {
            System.out.println((i + 1) + ". ");
            System.out.println("BookId: " + borrow.get(i).getBookId());
            System.out.println("Title: " + borrow.get(i).getBookName());
            System.out.println("Edition: " + borrow.get(i).getBookEdition());
            System.out.println("");
        }

        System.out.println("Do you want to return all books? [Y|N]: ");
        String returnConfirmation = scan.nextLine();

        if(returnConfirmation.equals("Y")){
            for (int i = 0; i < borrow.size(); i++) {
                Integer bookIndex = searchBook(borrow.get(i).getBookId().toString());
                Book temp = BookMenu.books.get(bookIndex);
                BookMenu.books.set(bookIndex, new Book(temp.getBookId(), temp.getBookName(), temp.getBookEdition(), temp.getBookQuantity() + 1));
            }

            transactionList.set(transactionIndex, new Transaction(data.getTransactionDate(), data.getcustomerId(), data.getTransactionId(), true, data.getBorrowedBookList()));

            System.out.println();
            System.out.println("Thank you");
            System.out.println("Press enter to continue...");
            scan.nextLine();
        }
       
    }

    private static void customerList() {
        int number = 0;
        System.out.println("+-----+------------------------------------+------------------------------------+--------------------+--------------------+");
        System.out.println("| No. |Customer Id                         |Customer Name                       | Max Book Limit     | Customer Rank      |");
        System.out.println("+-----+------------------------------------+------------------------------------+--------------------+--------------------+");
        for (Customer currentCustomer : CustomerMenu.customerArrayList) {
            number++;
            System.out.printf("|%-5s|%-36s|%-36s|%-20s|%-20s|%n", number, currentCustomer.getCustomerID(),currentCustomer.getUsername(), currentCustomer.getMaxBookLimit(), currentCustomer.getCustomerRank());
        }
        System.out.println("+-----+------------------------------------+--------------------+--------------------+");
    }

    private static void borrowedBookList() {
        int number = 0;
        System.out.println("+-----+------------------------------------+--------------------+--------------------+");
        System.out.println("| No. |Book Id                             | Book Name          | Book Edition       |");
        System.out.println("+-----+------------------------------------+--------------------+--------------------+");
        for (BorrowedBook currentBorrow : borrowedBook) {
            number++;
            System.out.printf("|%-5s|%-36s|%-20s|%-20s|%n", number, currentBorrow.getBookId(), currentBorrow.getBookName(), currentBorrow.getBookEdition());
        }
        System.out.println("+-----+------------------------------------+--------------------+--------------------+");
        System.out.println();

    }    
}
