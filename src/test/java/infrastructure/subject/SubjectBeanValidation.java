package infrastructure.subject;

import dto.Subject;
import dto.User;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class SubjectBeanValidation {
    Validator validator;
    ValidatorFactory validatorFactory;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    @Test
    public void testSubjectCannotHaveNullValue() throws Exception {
        Set<ConstraintViolation<Subject>> violations = validator.validate(new Subject());
        assertEquals(1, violations.size());
    }

    @Test
    public void testSubjectUserCapacityBounds() throws Exception {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User());
        }

        final Subject subject = new Subject();
        subject.setName("PG5100 Enterprise Programming");
        subject.setUsers(users);
        assertEquals(0, validator.validate(subject).size());

        subject.getUsers().add(new User());
        assertEquals(1, validator.validate(subject).size());
    }

    @Test
    public void testValidValuesShouldWork() throws Exception {
        Subject subject = new Subject();
        subject.setName("PG5100 Enterprise Programming");
        assertEquals(0, validator.validate(subject).size());
    }
}