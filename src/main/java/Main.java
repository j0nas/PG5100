import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class Main {
    static {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    @Inject
    private UserHandler userHandler;

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<UserService> serviceInstance = container.instance().select(UserService.class);
        UserService service = serviceInstance.get();

        service.printUsers();
    }
}
