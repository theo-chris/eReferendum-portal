package Domain;

public class User {

    private String email;
    private String fullName;
    private String dateOfBirth;
    private String password;
    private String homeAddress;
    private String BIC;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private int role;

    public boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    private boolean hasVoted;


    public User(int userRole,String userEmail, String userFullName, String userDOB, String userPassword, String userHomeAddress,String userBIC){
        this.role = userRole;
        this.email = userEmail;
        this.fullName = userFullName;
        this.dateOfBirth = userDOB;
        this.password = userPassword;
        this.homeAddress = userHomeAddress;
        this.BIC = userBIC;
        this.hasVoted = false;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBIC() {
        return BIC;
    }

    public void setBIC(String BIC) {
        this.BIC = BIC;
    }


}
