package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name="teacher_student",
            joinColumns=@JoinColumn(name="teacher_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
    private Set<Student> students = new HashSet<>();

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
        if(!student.getTeachers().contains(this)){
            student.getTeachers().add(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}