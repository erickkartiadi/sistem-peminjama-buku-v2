import java.time.LocalDate;
import java.util.*;

public class Transaction {
    private LocalDate transactionDate;
    private String customerId;
    private String transactionId;
    private Boolean isReturned;
    Vector<BorrowedBook> borrowedBookList = new Vector<>();

    public Transaction(LocalDate localDate, String customerId, String transactionId,Boolean isReturned, Vector<BorrowedBook> borrowedBooks ){
        super();
        this.transactionDate = localDate;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.isReturned = isReturned;
        this.borrowedBookList = borrowedBooks;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getcustomerId() {
        return customerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public Vector<BorrowedBook> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void setBorrowedBookList(Vector<BorrowedBook> borrowedBookList){
        this.borrowedBookList = borrowedBookList;
    }

}
