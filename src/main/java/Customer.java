import java.util.Date;
import java.util.UUID;

public class Customer extends User {
    private String CustomerID;
    private Integer MaxBookLimit;
    private Integer CustomerRank;

    public Customer(String username, String email, Date DOB, String phoneNumber, Date joinedDate, Integer maxBookLimit, Integer customerRank) {
        super(username, email, DOB, phoneNumber, joinedDate);
        CustomerID = UUID.randomUUID().toString().replace("-", "");
        MaxBookLimit = maxBookLimit;
        CustomerRank = customerRank;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public Integer getMaxBookLimit() {
        return MaxBookLimit;
    }

    public void setMaxBookLimit(Integer maxBookLimit) {
        MaxBookLimit = maxBookLimit;
    }

    public Integer getCustomerRank() {
        return CustomerRank;
    }

    public void setCustomerRank(Integer customerRank) {
        CustomerRank = customerRank;
    }
}
