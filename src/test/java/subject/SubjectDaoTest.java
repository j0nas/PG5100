package subject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubjectDaoTest {
    private SubjectDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new SubjectDao();
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void testCreate() throws Exception {
        Assert.assertNotNull(dao.create("TEST_SUBJECT"));
    }

    @Test
    public void testFind() throws Exception {
        final Subject subject = dao.create("TEST_SUBJECT");
        Assert.assertNotNull(dao.find(subject.getId()));
    }
}