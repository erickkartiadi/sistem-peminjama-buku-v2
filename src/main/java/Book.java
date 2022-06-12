import java.util.UUID;

public class Book {
    private final UUID bookId;
    private String bookName;
    private String bookEdition;
    private Integer bookQuantity;


    public Book(UUID bookId, String bookName, String bookEdition, Integer bookQuantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
        this.bookQuantity = bookQuantity;
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

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
