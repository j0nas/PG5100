import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import service.UserHandler;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Jonas on 26.11.2015.
 */
public class Main {
    @Inject
    private UserHandler userHandler;

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<UserService> serviceInstance =
                container.instance().select(UserService.class);
        UserService service = serviceInstance.get();

        service.printUsers();
    }
}
