import java.util.UUID;

public class BorrowedBook {
    private final UUID bookId;
    private String bookName;
    private String bookEdition;

    public BorrowedBook(UUID bookId, String bookName, String bookEdition) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
    }

    public UUID getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }
    

}
