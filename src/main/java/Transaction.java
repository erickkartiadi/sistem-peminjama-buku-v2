import java.time.LocalDate;
import java.util.*;

public class Transaction {
    private LocalDate transactionDate;
    private String customerId;
    private String transactionId;
    Vector<BorrowedBook> borrowedBookList = new Vector<>();

    public Transaction(LocalDate localDate, String customerId, String transactionId, Vector<BorrowedBook> borrowedBooks ){
        super();
        this.transactionDate = localDate;
        this.customerId = customerId;
        this.transactionId = transactionId;
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

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Vector<BorrowedBook> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void setBorrowedBookList(Vector<BorrowedBook> borrowedBookList){
        this.borrowedBookList = borrowedBookList;
    }

}
