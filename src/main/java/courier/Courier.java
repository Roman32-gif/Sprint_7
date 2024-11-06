package courier;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier login(String login) {
        this.login = login;
        return this;
    }

    public Courier password(String password) {
        this.password = password;
        return this;
    }

    public Courier firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
