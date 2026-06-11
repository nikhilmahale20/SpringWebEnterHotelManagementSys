package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.config.JPAUtil;
import org.example.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PaymentRepository {

    public void savePayment(
            Payment payment
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(payment);

            em.getTransaction().commit();

        } finally {

            em.close();
        }
    }

    public List<Payment> getAllPayments() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "FROM Payment",
                    Payment.class
            ).getResultList();

        } finally {

            em.close();
        }
    }

    public Payment findByBookingId(Long bookingId){

        EntityManager em =
                JPAUtil.getEntityManager();

        try{

            return em.createQuery(
                            "FROM Payment p WHERE p.booking.bookingId = :id",
                            Payment.class
                    )
                    .setParameter("id", bookingId)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } finally {

            em.close();
        }
    }


    public List<Payment>
    findPaymentsByCustomerId(
            Long customerId
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                            """
                            SELECT p
                            FROM Payment p
                            WHERE p.booking.customer.customerId
                                  = :customerId
                            """,
                            Payment.class
                    )
                    .setParameter(
                            "customerId",
                            customerId
                    )
                    .getResultList();

        } finally {

            em.close();
        }
    }
}