package Users;

public class User {
    private String name;
    private String username;
    private String password;
    private String adress;
    private int age;
    private String phone;
    private String bankAccount;
    private String country;

    public User(String name, String username, String password, String adress, int age, String phone, String bankAccount,
            String country) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.adress = adress;
        this.age = age;
        this.phone = phone;
        this.bankAccount = bankAccount;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", username=" + username + ", password=" + password + ", adress=" + adress
                + ", age=" + age + ", phone=" + phone + ", bankAccount=" + bankAccount + ", country=" + country + "]";
    }
}