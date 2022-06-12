import java.util.Date;

abstract class User {
    private String username;
    private String email;
    private Date DOB;
    private String phoneNumber;
    private Date joinedDate;

    public User(String username, String email, Date DOB, String phoneNumber, Date joinedDate) {
        this.username = username;
        this.email = email;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.joinedDate = joinedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
