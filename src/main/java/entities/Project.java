package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy="projects") //, cascade = CascadeType.PERSIST)
    private Set<Employee> employees = new HashSet<>();
    public Integer getId() {
        return id;
    }

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
//        if(!employee.getProjects().contains(this))
            employee.getProjects().add(this);
    }

    public void setId(Integer id) {
        this.id = id;
    }
}