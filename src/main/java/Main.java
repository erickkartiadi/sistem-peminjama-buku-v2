import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static Vector<BorrowedBook> borrowedBook = new Vector<>();
    static Vector<Transaction> transactionList = new Vector<>();

    public static void main(String[] args) {
        int choose;

        do {
            System.out.println("1. Add book");
            System.out.println("2. View books");
            System.out.println("3. Edit book");
            System.out.println("4. Remove book");
            System.out.println("5. Borrow book");
            System.out.println("6. Return book");
            System.out.println("7. Show Transaction");
            System.out.println("8. Manage User");
            System.out.println("9. Exit");
            System.out.print("Choose >> ");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    editBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    showTransaction();
                    break;
                case 8:
                    manageUser();
                    break;

            }
        } while (choose != 9);
    }

    // TODO nanti aku nambahin
    private static void manageUser() {
        CustomerMenu.showCustomerMenu();
    }

    private static int searchBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            Book data = books.get(i);

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

    private static void borrowBook() {
        viewBooks();
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
                Book data = books.get(idx[i]);
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
                inp = scan.nextInt();
                scan.nextLine();

            } while (inp < 1 || inp > 3);

            if (inp == 1) {
                break;
            } else if (inp == 2) {
                for (int i = 0; i < num; i++) {
                    Book data = books.get(idx[i]);
                    System.out.println("No. " + (i + 1));
                    System.out.println("ID: " + data.getBookId());
                    System.out.println("Title: " + data.getBookName());
                    System.out.println("Edition: " + data.getBookEdition());
                    System.out.println();
                }
                do {
                    System.out.println("Input No. from the list to be removed: ");
                    removedBook = scan.nextInt();
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
            }
        } while (true);

        if (num != 0) {
            Date d = new Date();

            LocalDate localDate = LocalDate.now();

            String tranId = "RE";
            int min = 0;
            int max = 9;

            Random random = new Random();

            int num1 = random.nextInt((max - min) + 1) + min;
            tranId = tranId + num1;

            System.out.println("The borrowed book list: ");
            for (int i = 0; i < num; i++) {
                Book data = books.get(idx[i]);
                System.out.println("ID: " + data.getBookId());
                System.out.println("Title: " + data.getBookName());
                System.out.println("Edition: " + data.getBookEdition());
                System.out.println();

                borrowedBook.add(new BorrowedBook(data.getBookId(), data.getBookName(), data.getBookEdition()));
            }
            // TODO ganti customer_id
            transactionList.add(new Transaction(localDate, "TES", tranId, borrowedBook));

        } else {
            System.out.println("You don't have book on the borrowed list");
            System.out.println("Press enter to continue");
            scan.nextLine();

            System.out.println("");
        }

        num = 0;
    }

    private static void showTransaction() {
        Integer in;

        for (int i = 0; i < transactionList.size(); i++) {
            Transaction data = transactionList.get(i);
            System.out.println("No." + (i + 1));
            System.out.println("Transaction Id: " + data.getTransactionId());
            System.out.println("Customer Id: " + data.getcustomerId());
            System.out.println("");
        }
        do {
            System.out.println("Choose No. of transaction to view borrowed book list");
            in = scan.nextInt();
        } while (in <= 0 || in > transactionList.size());

        System.out.println("");

        if (in == 0) {
            return;
        } else {
            Vector<BorrowedBook> borrow = transactionList.get(in - 1).getBorrowedBookList();
            System.out.println("List of borrowed book: ");
            for (int j = 0; j < borrow.size(); j++) {
                System.out.println((j + 1) + ". ");
                System.out.println("BookId: " + borrow.get(j).getBookId());
                System.out.println("Title: " + borrow.get(j).getBookName());
                System.out.println("Edition: " + borrow.get(j).getBookEdition());
                System.out.println("");
            }
        }

    }

    private static void removeBook() {
        // TODO ganti remove dari index jadi ID
        int bookNumber;
        viewBooks();
        System.out.print("Enter the number of the book you want to delete: ");
        bookNumber = Integer.parseInt(scan.nextLine());

        books.remove(bookNumber - 1);
    }

    private static void editBook() {
        int bookNumber;
        viewBooks();
        System.out.print("Enter the number of the book you want to edit: ");
        bookNumber = Integer.parseInt(scan.nextLine());

        Book book = books.get(bookNumber - 1);
        int choose;
        do {
            System.out.println("Which attribute you want to edit");
            System.out.println("1. Book name");
            System.out.println("2. Book edition");
            System.out.println("3. Book quantity");
            System.out.println("4. Cancel edit");
            System.out.print("Choose >> ");
            choose = Integer.parseInt(scan.nextLine());

            switch (choose) {
                case 1:
                    System.out.print("Enter new book name: ");
                    book.setBookName(scan.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new boo k edition: ");
                    book.setBookEdition(scan.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new book quantity: ");
                    book.setBookQuantity(Integer.parseInt(scan.nextLine()));
                    break;
            }

        } while (choose != 4);
    }

    private static void viewBooks() {
        int number = 0;
        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
        System.out.println("| No. |Book Id                             | Book Name          | Book Edition       |Book Quantity  |");
        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
        for (Book currentBook : books) {
            int bookQuantity = currentBook.getBookQuantity();

            if (bookQuantity > 0) {
                number++;
                System.out.printf("|%-5s|%-36s|%-20s|%-20s|%-15s|%n", number, currentBook.getBookId(), currentBook.getBookName(), currentBook.getBookEdition(), bookQuantity);
            }
        }
        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");

    }

    static public void addBook() {
        UUID bookId = UUID.randomUUID();
        System.out.print("Enter book name: ");
        String bookName = scan.nextLine();
        System.out.print("Enter book edition: ");
        String bookEdition = scan.nextLine();
        System.out.print("Enter book quantity: ");
        Integer bookQuantity = Integer.parseInt(scan.nextLine());

        Book book = new Book(bookId, bookName, bookEdition, bookQuantity);
        books.add(book);
    }

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
        String returnAll = scan.nextLine();
        if (returnAll.equals("Y")) {
            for (int i = 0; i < borrow.size(); i++) {
                Integer bookIndex = searchBook(borrow.get(i).getBookId().toString());
                Book temp = books.get(bookIndex);
                books.set(bookIndex, new Book(temp.getBookId(), temp.getBookName(), temp.getBookEdition(), temp.getBookQuantity() + 1));
            }
        } else {
            while (true) {
                for (int i = 0; i < borrow.size(); i++) {
                    System.out.println((i + 1) + ". ");
                    System.out.println("BookId: " + borrow.get(i).getBookId());
                    System.out.println("Title: " + borrow.get(i).getBookName());
                    System.out.println("Edition: " + borrow.get(i).getBookEdition());
                    System.out.println("");
                }
                System.out.print("Please input the book ID: ");
                String bookID = scan.nextLine();
                Integer bookIndex = searchBook(bookID);
                Book temp = books.get(bookIndex);
                books.set(bookIndex, new Book(temp.getBookId(), temp.getBookName(), temp.getBookEdition(), temp.getBookQuantity() + 1));
                System.out.println("Do you want to return another book in this transaction? [Y|N]: ");
                String returnChoice = scan.nextLine();
                if (returnChoice.equals("Y")) {
                    continue;
                } else {
                    break;
                }
            }
        }
        System.out.println("Thank you");
        System.out.println();
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }

}
