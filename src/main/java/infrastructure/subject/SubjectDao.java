package infrastructure.subject;

import dto.Subject;

import java.util.List;

public interface SubjectDao {
    Subject persist(Subject subject);

    Subject findById(int id);

    List<Subject> getAll();
}
