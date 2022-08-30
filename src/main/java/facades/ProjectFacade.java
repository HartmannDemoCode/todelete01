package facades;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectFacade {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public Project create(Project p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Set<Employee> employees = new HashSet<>();
        p.getEmployees().forEach((employee)->{
            if(employee.getId()!=null && em.find(Employee.class, employee.getId())!=null){
                em.merge(employee);
            } else {
                em.persist(employee);
            }
        });
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    public static void main(String[] args) {
        Project p = new Project("Learn JPA");
        Employee e = new Employee("Erling");
        p.addEmployee(e);
        ProjectFacade pf = new ProjectFacade();
        pf.create(p);
    }

    public Project updateProject(Project s) {
        //return null;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Integer delete(Project s) {
        //return null;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Project findById(Integer id) {
        //return null;
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Project> getAll() {
        //return null;
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
