import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookMenu {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    public static void bookMenu(){
        int choose;

        do {
            System.out.println("1. Add book");
            System.out.println("2. View books");
            System.out.println("3. Edit book");
            System.out.println("4. Remove book");
            System.out.println("5. Back to Menu");
            System.out.print(">> ");
            choose = scan.nextInt();
            scan.nextLine();

            switch (choose) {
                case 1:
                    GlobalFunction.cls();
                    addBook();
                    break;
                case 2:
                    GlobalFunction.cls();
                    bookList();
                    break;
                case 3:
                    GlobalFunction.cls();
                    editBook();
                    break;
                case 4:
                    GlobalFunction.cls();
                    removeBook();
                    break;


            }
            GlobalFunction.cls();
        } while (choose != 5);
    }

    private static void bookList(){
        viewBooks();
        GlobalFunction.pressContinue();
    }

    private static void removeBook() {
        int bookNumber;
        viewBooks();
        System.out.print("Enter the number of the book you want to delete: ");
        bookNumber = Integer.parseInt(scan.nextLine());

        books.remove(bookNumber - 1);
        System.out.println();
        System.out.println("Book has been successfully deleted");
        GlobalFunction.pressContinue();
    }

    private static void editBook() {
        int bookNumber;
        if(!getAvailability()){
            GlobalFunction.pressContinue();
            return;
        }
        viewBooksAdmin();
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
                    System.out.print("Enter new book edition: ");
                    book.setBookEdition(scan.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new book quantity: ");
                    book.setBookQuantity(Integer.parseInt(scan.nextLine()));
                    break;
            }

            if(choose != 4){
                System.out.println();
                System.out.println("Book has been successfully edited!");
                GlobalFunction.pressContinue();
            }

        } while (choose != 4);
    }

    public static void viewBooks() {
        int number = 0;
        boolean isExist = false;
        if(books.isEmpty()){
            System.out.println();
            System.out.println("There is no book available");
            return;
        }

        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
        System.out.println("| No. |Book Id                             | Book Name          | Book Edition       |Book Quantity  |");
        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
        for (Book currentBook : books) {
            number++;
            if(currentBook.getBookQuantity() == 0){
                continue;
            } else if(currentBook.getBookQuantity() > 0){
                isExist = true;
                System.out.printf("|%-5s|%-36s|%-20s|%-20s|%-15s|%n", number, currentBook.getBookId(), currentBook.getBookName(), currentBook.getBookEdition(), currentBook.getBookQuantity());
            }
        }
        System.out.println("+-----+------------------------------------+--------------------+--------------------+---------------+");
        
        if(isExist == false){
            System.out.println();
            System.out.println("There is no book available");
        }
    }

    public static void viewBooksAdmin() {
        int number = 0;

        if(books.isEmpty()){
            System.out.println();
            System.out.println("There is no book available");
            return;
        }

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
        Integer bookQuantity = -1;

        UUID bookId = UUID.randomUUID();
        System.out.print("Enter book name: ");
        String bookName = scan.nextLine();
        System.out.print("Enter book edition: ");
        String bookEdition = scan.nextLine();
        
        do{
            System.out.print("Enter book quantity: ");
            bookQuantity = scan.nextInt();
            scan.nextLine();
        } while(bookQuantity < 1);
        

        Book book = new Book(bookId, bookName, bookEdition, bookQuantity);
        books.add(book);

        System.out.println();
        System.out.println("Book has been successfully inputed");
        GlobalFunction.pressContinue();
    }

    public static boolean getAvailability(){
        if(books.isEmpty()){
            return false;
        }

        boolean exist = false;

        for(Book x : books){
            if(x.getBookQuantity() > 0){
                exist = true;
            }
        }
        return exist;
    }
}
