package courier;

public class CourierNoPassword {
    private String login;
    private String password;

    public CourierNoPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierNoPassword getLogin(Courier courier) {
        return new CourierNoPassword(courier.getLogin(), "");
    }
}
