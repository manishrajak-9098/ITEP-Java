package com.jpa.main;

import java.util.List;
import java.util.Scanner;

import com.jpa.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class HibernateMain {

    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mypunit");

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            while (true) {
                System.out.println("\n===== STUDENT CRUD + JPQL =====");
                System.out.println("1. Insert");
                System.out.println("2. Read by ID");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Show All Students");
                System.out.println("6. Search by Course");
                System.out.println("7. Exit");
                System.out.print("Choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1: insertStudent(); break;
                    case 2: readStudent(); break;
                    case 3: updateStudent(); break;
                    case 4: deleteStudent(); break;
                    case 5: showAllStudents(); break;
                    case 6: searchByCourse(); break;
                    case 7:
                        emf.close();
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error in Main Menu");
            e.printStackTrace();
        }
    }

    // -------insert-----------
    static void insertStudent() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Student st = new Student();

            System.out.print("Name: ");
            st.setUsername(sc.next());

            System.out.print("Age: ");
            st.setAge(sc.nextInt());

            System.out.print("Salary: ");
            st.setSalary(sc.nextDouble());

            System.out.print("Course: ");
            st.setCourse(sc.next());

            em.persist(st);
            em.getTransaction().commit();

            System.out.println("Student Inserted");

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("❌ Insert Failed");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- READ ----------------
    static void readStudent() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            System.out.print("Enter ID: ");
            Student s = em.find(Student.class, sc.nextInt());

            if (s != null) {
                printStudent(s);
            } else {
                System.out.println(" Student Not Found");
            }

        } catch (Exception e) {
            System.out.println(" Read Error");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- UPDATE ----------------
    static void updateStudent() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            System.out.print("Enter ID: ");
            Student s = em.find(Student.class, sc.nextInt());

            if (s != null) {
                System.out.print("New Salary: ");
                s.setSalary(sc.nextDouble());

                System.out.print("New Course: ");
                s.setCourse(sc.next());

                em.merge(s);
                em.getTransaction().commit();
                System.out.println(" Student Updated");
            } else {
                System.out.println(" Student Not Found");
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(" Update Failed");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- DELETE ----------------
    static void deleteStudent() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            System.out.print("Enter ID: ");
            Student s = em.find(Student.class, sc.nextInt());

            if (s != null) {
                em.remove(s);
                em.getTransaction().commit();
                System.out.println(" Student Deleted");
            } else {
                System.out.println(" Student Not Found");
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println(" Delete Failed");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- SHOW ALL ----------------
    static void showAllStudents() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            TypedQuery<Student> query =
                    em.createQuery("from Student", Student.class);

            List<Student> list = query.getResultList();

            for (Student s : list) {
                printStudent(s);
            }

        } catch (Exception e) {
            System.out.println(" Fetch Error");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- SEARCH BY COURSE ----------------
    static void searchByCourse() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            System.out.print("Enter Course: ");
            String course = sc.next();

            TypedQuery<Student> query =
                    em.createQuery(
                            "from Student s where s.course = :course",
                            Student.class);

            query.setParameter("course", course);

            List<Student> list = query.getResultList();

            if (list.isEmpty()) {
                System.out.println(" No Students Found");
            } else {
                for (Student s : list) {
                    printStudent(s);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Search Error");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
        }
    }

    // ---------------- PRINT ----------------
    static void printStudent(Student s) {
        System.out.println(
                s.getId() + " | " +
                s.getUsername() + " | " +
                s.getAge() + " | " +
                s.getSalary() + " | " +
                s.getCourse()
        );
    }
}
