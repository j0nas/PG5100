package user;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserBeanValidationTest {
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testUserCannotHaveNullValues() throws Exception {
        User user = new User(1, null, null, null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        //violations.forEach(System.out::println);
        assertEquals(2, violations.size());
    }

    @Test
    public void testValidValuesShouldWork() throws Exception {
        User user = new User(1, "jonas.jensen@msn.com", "thisIsAValidPassword1", UserType.STUDENT);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        violations.forEach(System.out::println);
        assertEquals(0, violations.size());
    }
}
