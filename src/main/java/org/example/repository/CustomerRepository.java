package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.config.JPAUtil;
import org.example.entity.Customer;
import org.example.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    public void saveCustomer(Customer customer) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            em.persist(customer);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw new DataAccessException(
                    "Customer Save Failed"
            );

        } finally {
            em.close();
        }
    }

    public Optional<Customer> findByEmail(
            String email
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            List<Customer> customers =
                    em.createQuery(
                                    """
                                    SELECT c
                                    FROM Customer c
                                    WHERE c.email = :email
                                    """,
                                    Customer.class
                            )
                            .setParameter(
                                    "email",
                                    email
                            )
                            .getResultList();

            return customers.stream()
                    .findFirst();

        } finally {

            em.close();
        }
    }

    public List<Customer> getAllCustomers() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    """
                    SELECT c
                    FROM Customer c
                    """,
                    Customer.class
            ).getResultList();

        } finally {

            em.close();
        }
    }
}