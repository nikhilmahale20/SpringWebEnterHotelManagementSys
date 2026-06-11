package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.config.JPAUtil;
import org.example.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookingRepository {

    public Booking findById(Long bookingId) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.find(
                    Booking.class,
                    bookingId
            );

        } finally {

            em.close();
        }
    }

    public void updateBooking(
            Booking booking
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(booking);

            em.getTransaction().commit();

        } finally {

            em.close();
        }
    }

    public void saveBooking(Booking booking) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(booking);

            em.getTransaction().commit();

        } finally {

            em.close();
        }
    }

    public List<Booking> getAllBookings() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "FROM Booking",
                    Booking.class
            ).getResultList();

        } finally {

            em.close();
        }
    }

    public List<Booking> getBookingsByRoomId(
            Long roomId
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                            """
                            SELECT b
                            FROM Booking b
                            WHERE b.room.roomId = :roomId
                            """,
                            Booking.class
                    )
                    .setParameter(
                            "roomId",
                            roomId
                    )
                    .getResultList();

        } finally {

            em.close();
        }
    }

    public List<Booking> getBookingsByCustomerId(
            Long customerId
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                            """
                            SELECT b
                            FROM Booking b
                            WHERE b.customer.customerId = :customerId
                            """,
                            Booking.class
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