package TutorialSpringMVC.Service;

public class CustomerServiceFactory {

    private static ICustomerService singleton;

    public static synchronized ICustomerService getInstance() {
        if (singleton == null) {
            singleton = new SimpleICustomerServiceImpl();
        }
        return singleton;
    }
}
