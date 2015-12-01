package user;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@H2Transaction
public class H2TransactionInterceptor {
    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        /*
        final Object[] parameters = ic.getParameters();
        System.out.println(Arrays.toString(parameters));
        System.out.println(parameters[0].toString());
        // System.out.println(parameters[1].toString());
        // System.out.println(parameters[2].toString());

        // if (Math.random() > 0)
        //   return ic.proceed();
        final Set<ConstraintViolation<user.User>> violations =
                Validation.buildDefaultValidatorFactory().getValidator()
                        .validate(new user.User(0, parameters[0].toString(), "parameters[1].toString", user.UserType.TEACHER));
        if (violations.size() > 0) {
            throw new ValidationException(violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));
        } TODO */

        final EntityManager entityManager = ((H2UserDao) ic.getTarget()).getEntityManager();
        entityManager.getTransaction().begin();
        try {
            return ic.proceed();
        } finally {
            try {
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                System.out.printf(e.toString());
            }
        }
    }
}
