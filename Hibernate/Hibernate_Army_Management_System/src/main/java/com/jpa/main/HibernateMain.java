package com.jpa.main;


import java.util.Scanner;
import com.jpa.model.Army;
import jakarta.persistence.*;

public class HibernateMain {

    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mypunit");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== ARMY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Soldier");
            System.out.println("2. View Soldier by ID");
            System.out.println("3. Update Soldier");
            System.out.println("4. Delete Soldier");
            System.out.println("5. View All Soldiers");
            System.out.println("6. Search by Rank");
            System.out.println("7. Search by Unit");
            System.out.println("8. Search by Posting");
            System.out.println("9. Search by Experience");
            System.out.println("10. Sort by Salary");
            System.out.println("11. Sort by Age");
            System.out.println("12. Count Soldiers");
            System.out.println("13. Average Salary");
            System.out.println("14. Pagination");
            System.out.println("15. Exit");
            System.out.print("Choice: ");

            int ch = getPositiveInt();

            switch (ch) {
                case 1 -> addSoldier();
                case 2 -> viewById();
                case 3 -> updateSoldier();
                case 4 -> deleteSoldier();
                case 5 -> viewAll();
                case 6 -> searchByRank();
                case 7 -> searchByUnit();
                case 8 -> searchByPosting();
                case 9 -> searchByExperience();
                case 10 -> sortBySalary();
                case 11 -> sortByAge();
                case 12 -> countSoldiers();
                case 13 -> avgSalary();
                case 14 -> pagination();
                case 15 -> {
                    emf.close();
                    System.out.println("Application Closed");
                    System.exit(0);
                }
                default -> System.out.println(" Invalid Choice");
            }
        }
    }

    // ================= ADD =================
    static void addSoldier() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Army s = new Army();

        String name;
        while (true) {
            System.out.print("Name: ");
            name = getString();
            if (name.matches("[a-zA-Z ]+")) {
                s.setName(name);
                break;
            } else {
                System.out.println(" Sirf characters enter karo (no numbers/symbols).");
            }
        }

        System.out.print("Age: ");
        s.setAge(getPositiveInt());

        System.out.print("Rank: ");
        s.setRank(getString());

        System.out.print("Unit: ");
        s.setUnit(getString());

        System.out.print("Posting: ");
        s.setPosting(getString());

        System.out.print("Experience: ");
        s.setExperience(getPositiveInt());

        System.out.print("Salary: ");
        s.setSalary(getPositiveDouble());

        em.persist(s);
        em.getTransaction().commit();
        em.close();

        System.out.println(" Soldier Added");
    }

    // ================= VIEW =================
    static void viewById() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Enter ID: ");
        int id = getPositiveInt();

        Army s = em.find(Army.class, id);
        if (s != null) print(s);
        else System.out.println(" Soldier Not Found");

        em.close();
    }

    // ================= UPDATE =================
    static void updateSoldier() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.print("Enter ID: ");
        int id = getPositiveInt();
        Army s = em.find(Army.class, id);

        if (s != null) {
            System.out.print("New Rank: ");
            s.setRank(getString());

            System.out.print("New Salary: ");
            s.setSalary(getPositiveDouble());

            em.merge(s);
            em.getTransaction().commit();
            System.out.println(" Updated");
        } else {
            em.getTransaction().rollback();
            System.out.println(" Not Found");
        }
        em.close();
    }

    // ================= DELETE =================
    static void deleteSoldier() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.print("Enter ID: ");
        int id = getPositiveInt();
        Army s = em.find(Army.class, id);

        if (s != null) {
            em.remove(s);
            em.getTransaction().commit();
            System.out.println(" Deleted");
        } else {
            em.getTransaction().rollback();
            System.out.println(" Not Found");
        }
        em.close();
    }

    // ================= OTHERS =================
    static void viewAll() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("from Army", Army.class)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void searchByRank() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Rank: ");
        String r = getString();
        em.createQuery("from Army a where a.rank=:r", Army.class)
          .setParameter("r", r)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void searchByUnit() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Unit: ");
        String u = getString();
        em.createQuery("from Army a where a.unit=:u", Army.class)
          .setParameter("u", u)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void searchByPosting() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Posting: ");
        String p = getString();
        em.createQuery("from Army a where a.posting=:p", Army.class)
          .setParameter("p", p)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void searchByExperience() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Min Experience: ");
        int e = getPositiveInt();
        em.createQuery("from Army a where a.experience>=:e", Army.class)
          .setParameter("e", e)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void sortBySalary() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("from Army a order by a.salary desc", Army.class)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void sortByAge() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("from Army a order by a.age asc", Army.class)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    static void countSoldiers() {
        EntityManager em = emf.createEntityManager();
        Long c = em.createQuery("select count(a) from Army a", Long.class)
                   .getSingleResult();
        System.out.println("Total Soldiers = " + c);
        em.close();
    }

    static void avgSalary() {
        EntityManager em = emf.createEntityManager();
        Double avg = em.createQuery("select avg(a.salary) from Army a", Double.class)
                       .getSingleResult();
        System.out.println("Average Salary = " + avg);
        em.close();
    }

    static void pagination() {
        EntityManager em = emf.createEntityManager();
        System.out.print("Page No: ");
        int page = getPositiveInt();
        System.out.print("Records/Page: ");
        int size = getPositiveInt();

        em.createQuery("from Army", Army.class)
          .setFirstResult((page - 1) * size)
          .setMaxResults(size)
          .getResultList().forEach(HibernateMain::print);
        em.close();
    }

    // ================= VALIDATION =================
    static int getPositiveInt() {
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n > 0) return n;
                System.out.print(" Positive number: ");
            } catch (Exception e) {
                System.out.print(" Number hi daalo: ");
            }
        }
    }

    static double getPositiveDouble() {
        while (true) {
            try {
                double n = Double.parseDouble(sc.nextLine());
                if (n > 0) return n;
                System.out.print("Enter Positive value: ");
            } catch (Exception e) {
                System.out.print(" Number hi daalo: ");
            }
        }
    }

    static String getString() {
        while (true) {
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.print(" Empty nahi: ");
        }
    }

    static void print(Army s) {
        System.out.println(
            s.getId()+" | "+s.getName()+" | "+s.getAge()+" | "+
            s.getRank()+" | "+s.getUnit()+" | "+s.getPosting()+" | "+
            s.getExperience()+" yrs | "+s.getSalary()
        );
    }
}
