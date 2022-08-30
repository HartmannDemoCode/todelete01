package facades;

import entities.ClassRoom;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentFacade implements DataFacade{
    //Step 1: Create Factory
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    //Step 2: Create getEntityManager method
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    @Override
    public Student create(Student s) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
        return s;
    }

    public static void main(String[] args) {
        StudentFacade sf = new StudentFacade();
        Student s = sf.create(new Student("Katrine",43));
        Teacher t = sf.createTeacher(new Teacher("Kaj"));
        Teacher t2 = sf.createTeacher(new Teacher("Kalle"));


//        System.out.println(s);
        List<Student> students = sf.getAll();
        System.out.println("STUDENT LIST:");
        students.forEach(System.out::println);
        System.out.println("FOUND");
        System.out.println(sf.findById(2));
        System.out.println("MERGED/UPDATED");
        s.setAge(13);
        System.out.println(sf.updateStudent(s));
        sf.getAll().forEach(System.out::println);
//        System.out.println("DELETE");
//        sf.delete(s);
//        sf.getAll().forEach(System.out::println);
        System.out.println("ADD CLASSROOM");
        ClassRoom cr = new ClassRoom("0.03", true);
        sf.getAll().forEach(
                (student)->{
                    cr.addStudent(student);
                    t.addStudents(student);
                    t2.addStudents(student);
        });
        sf.createClass(cr);
        sf.updateTeacher(t);
        sf.updateTeacher(t2);
    }

    @Override
    public Student findById(Integer id) {
        EntityManager em = getEntityManager();
        Student found = em.find(Student.class, id);
        em.close();
        return found;
    }

    @Override
    public Student updateStudent(Student s) {
        EntityManager em = getEntityManager();
        Student merged;
        try {
            em.getTransaction().begin();
            merged = em.merge(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return merged;
    }

    public Teacher updateTeacher(Teacher t) {
        EntityManager em = getEntityManager();
        Teacher merged;
        try {
            em.getTransaction().begin();
            merged = em.merge(t);
            t.getStudents().forEach((student)->{
                em.merge(student);
            });
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return merged;
    }
    @Override
    public Integer delete(Student s) {
        EntityManager em = getEntityManager();
        Student found = em.find(Student.class, s.getId());
        em.getTransaction().begin();
        em.remove(found);
        em.getTransaction().commit();
        em.close();
        return s.getId();
    }

    @Override
    public List<Student> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT m FROM Student m", Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public ClassRoom createClass(ClassRoom cr){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cr);
        cr.getStudents().forEach((student)->{
            em.merge(student);
        });
        em.getTransaction().commit();
        em.close();
        return cr;
    }

    public Teacher createTeacher(Teacher t){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        t.getStudents().forEach((student)->{
            em.merge(student);
        });
        em.getTransaction().commit();
        em.close();
        return t;
    }
}
