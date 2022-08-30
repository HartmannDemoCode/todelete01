package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(
            name="EMP_PROJ",
            joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="ID"))
    private Set<Project> projects = new HashSet<>();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
        if(!project.getEmployees().contains(this))
            project.getEmployees().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}