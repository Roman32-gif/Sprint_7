package courier;

public class CourierNoLogin {
    private String password;
    private String login;

    public CourierNoLogin(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public static CourierNoLogin getPassword(Courier courier) {
        return new CourierNoLogin("", courier.getPassword());
    }
}
