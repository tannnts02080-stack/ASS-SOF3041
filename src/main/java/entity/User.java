package entity;

import java.sql.Date;

public class User {
    private String id;
    private String password;
    private String fullname;
    private Date birthday;
    private Boolean gender;   // <-- kiểu Boolean (đúng)
    private String phone;
    private String email;
    private int role;

    public User() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }  // <-- phương thức đúng

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }
}
