package Users;

public class User {
    private String name; // NOMBRE.
    private String username; // USERNAME.
    private String password; // CONTRASEÑA
    private String adress; // DIRECCIÓN.
    private int age; // EDAD.
    private String phone; // NÚMERO DE TELÉFONO.
    private String bankAccount; // CUENTA BANCARIA.
    private String country; // PAÍS.

    /**
     * Método constructor.
     * @param name - Nombre.
     * @param username - Username.
     * @param password - Contraseña.
     * @param adress - Dirección.
     * @param age - Edad.
     * @param phone - Número Telefonica.
     * @param bankAccount - Cuenta Bancaria.
     * @param country - País.
     */
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

    /**
     * Método para obtener el nombre.
     * @return String - Nombre.
     */
    public String getName() {
        return name;
    }

    /**
     * Método poner el nombre.
     * @param name - Nuevo Nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para obtener la edad.
     * @return Integer - Edad.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Método para poner la edad.
     * @param age - Nueva edad.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Método para obtener el número telefonico.
     * @return String - Número telefonico.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Método para poner el telefono.
     * @param phone - Nuevo telefono.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Método para obtener el usuario.
     * @return String - UserName.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Método para poner usuario.
     * @param username - Nuevo usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método para obtener la contraseña
     * @return String - Contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método para poner la contraseña.
     * @param password - Nueva contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método para obtener la dirección.
     * @return String - Dirección.
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Método para la dirección.
     * @param adress - Nueva dirección.
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Método para poner la edad.
     * @param age - Nueva edad.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Método para obtener la cuenta bancaría.
     * @return String - Cuenta bancaría.
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Método para poner una cuenta bancaria.
     * @param bankAccount - Nueva Cuenta bancaría.
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * Método para obtener el país.
     * @return String - País.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Método para poner el país.
     * @param country - Nuevo país.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", username=" + username + ", password=" + password + ", adress=" + adress
                + ", age=" + age + ", phone=" + phone + ", bankAccount=" + bankAccount + ", country=" + country + "]";
    }
}