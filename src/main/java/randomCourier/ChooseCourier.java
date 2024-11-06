package randomCourier;
import courier.Courier;
import static generateRandom.RandomCourier.generateRandomString;

public class ChooseCourier {
    public static Courier chooseRandomCourier() {
        return new Courier()
                .login(generateRandomString(8))
                .password(generateRandomString(12))
                .firstName(generateRandomString(15));
    }
}
