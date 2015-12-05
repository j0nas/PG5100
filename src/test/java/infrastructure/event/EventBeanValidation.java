package infrastructure.event;

import dto.Event;
import dto.EventType;
import dto.Subject;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EventBeanValidation {
    Validator validator;
    ValidatorFactory validatorFactory;
    Event event;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();

        Subject subject = new Subject();
        subject.setName("TEST_NAME");

        event = new Event();
        event.setType(EventType.LECTURE);
        event.setTitle("TEST_TITLE");
        event.setSubject(subject);
        event.setStartTime(LocalDateTime.now());
        event.setEndTime(LocalDateTime.now().plusHours(3));
    }

    @Test
    public void testEventsCannotHaveNullValues() throws Exception {
        assertEquals(5, validator.validate(new Event()).size());
    }

    @Test
    public void testTitleConstraints() throws Exception {
        String tooLongString = "THIS_STRING_EXCEEDS_25_CHARACTERS";
        event.setTitle(tooLongString);
        assertEquals(1, validator.validate(event).size());

        String tooShortString = "NO";
        event.setTitle(tooShortString);
        assertEquals(1, validator.validate(event).size());

        String validTitle = "BETWEEN_5_AND_25_CHARS";
        event.setTitle(validTitle);
        assertEquals(0, validator.validate(event).size());
    }

    @Test
    public void testValidValuesShouldWork() throws Exception {
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        violations.forEach(System.out::println);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNeedsValidSubject() throws Exception {
        event.setSubject(new Subject());
        assertEquals(1, validator.validate(event).size());
    }
}