package facades;

import entities.Student;

import java.util.List;

public interface DataFacade {
    Student create(Student s);
    Student updateStudent(Student s);
    Integer delete(Student s);
    Student findById(Integer id);
    List<Student> getAll();
}
