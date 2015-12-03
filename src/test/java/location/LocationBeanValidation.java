package location;

import dto.Location;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LocationBeanValidation {
    Validator validator;
    ValidatorFactory validatorFactory;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    @Test
    public void testLocationCannotHaveNullValues() throws Exception {
        Set<ConstraintViolation<Location>> violations = validator.validate(new Location());
        assertEquals(2, violations.size());
    }

    @Test
    public void testValidValuesShouldWork() throws Exception {
        Location location = new Location();
        location.setRoom("Auditorium");
        location.setBuilding("Campus Galleriet");
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertEquals(0, violations.size());
    }


}