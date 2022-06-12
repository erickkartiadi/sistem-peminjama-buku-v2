import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookMenu {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        int choose;

        do {
            System.out.println("1. Add book");
            System.out.println("2. View books");
            System.out.println("3. Edit book");
            System.out.println("4. Remove book");
            System.out.println("5. Exit");
            System.out.print(">> ");
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


            }
        } while (choose != 5);
    }

    private static void removeBook() {
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
            number++;

            System.out.printf("|%-5s|%-36s|%-20s|%-20s|%-15s|%n", number, currentBook.getBookId(), currentBook.getBookName(), currentBook.getBookEdition(), currentBook.getBookQuantity());
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
}
