package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_room")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private boolean hasBlackBoard;
    @OneToMany(mappedBy = "classRoom")
    private List<Student> students = new ArrayList();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        if(student.getClassRoom()==null)
            student.setClassRoom(this);
    }

    public ClassRoom(String name, boolean hasBlackBoard) {
        this.name = name;
        this.hasBlackBoard = hasBlackBoard;
    }

    public ClassRoom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasBlackBoard() {
        return hasBlackBoard;
    }

    public void setHasBlackBoard(boolean hasBlackBoard) {
        this.hasBlackBoard = hasBlackBoard;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasBlackBoard=" + hasBlackBoard +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }
}