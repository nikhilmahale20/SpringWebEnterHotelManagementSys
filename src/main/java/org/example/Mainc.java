package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Customer;

public class Mainc {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hotelPU");

        EntityManager em1 =
                emf.createEntityManager();

        Customer c1 =
                em1.find(Customer.class, 1L);

        em1.close();

        System.out.println(
                "First Session Completed"
        );

        EntityManager em2 =
                emf.createEntityManager();

        Customer c2 =
                em2.find(Customer.class, 1L);

        em2.close();

        System.out.println(
                "Second Session Completed"
        );

        emf.close();
    }
}