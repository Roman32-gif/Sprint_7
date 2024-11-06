package courier;

public class CourierFakeInfo {
    private String login;
    private String password;

    public CourierFakeInfo (String login, String password) {
        this.login = login;
        this.password = password;
    }

    //метод заполнения несуществующим логином
    public static CourierFakeInfo  fakeLoginFromCourier(Courier courier) {
        return new CourierFakeInfo (courier.getLogin() + "frebg", courier.getPassword());
    }

    //метод заполнения несущуствующим паролем
    public static CourierFakeInfo  fakePasswordFromCourier(Courier courier) {
        return new CourierFakeInfo (courier.getLogin(), courier.getPassword() + "77777");
    }

    public static CourierFakeInfo  dataFromCourier (Courier courier) {
        return new CourierFakeInfo (courier.getLogin(), courier.getPassword());
    }

}