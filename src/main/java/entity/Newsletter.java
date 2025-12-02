package entity;

import java.sql.Date;

public class Newsletter {
    private int id;
    private String email;
    private Date subscribedDate;

    public Newsletter() {}

    public Newsletter(int id, String email, Date subscribedDate) {
        this.id = id;
        this.email = email;
        this.subscribedDate = subscribedDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getSubscribedDate() { return subscribedDate; }
    public void setSubscribedDate(Date subscribedDate) { this.subscribedDate = subscribedDate; }
}
