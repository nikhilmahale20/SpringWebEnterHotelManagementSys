package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.config.JPAUtil;
import org.example.entity.Room;
import org.example.exception.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class RoomRepository {

    public void saveRoom(Room room) {

        EntityManager em =
                JPAUtil.getEntityManager();

        EntityTransaction tx =
                em.getTransaction();

        try {

            tx.begin();

            em.persist(room);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw new DataAccessException(
                    "Database Operation Failed"
            );

        } finally {

            em.close();
        }
    }

    public void deleteRoom(Long roomId) {

        EntityManager em =
                JPAUtil.getEntityManager();

        EntityTransaction tx =
                em.getTransaction();

        try {

            tx.begin();

            Room room =
                    em.find(
                            Room.class,
                            roomId
                    );

            if(room != null) {

                em.remove(room);
            }

            tx.commit();

        } catch (Exception e) {

            if(tx.isActive()) {
                tx.rollback();
            }

            throw new DataAccessException(
                    "Room Delete Failed"
            );

        } finally {

            em.close();
        }
    }
    public List<Room> getAllRooms() {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "FROM Room",
                    Room.class
            ).getResultList();

        } finally {

            em.close();
        }
    }

    public Optional<Room> findRoomById(
            Long roomId
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            Room room =
                    em.find(Room.class, roomId);

            return Optional.ofNullable(room);

        } finally {

            em.close();
        }
    }

    public void updateRoom(Room room) {

        EntityManager em =
                JPAUtil.getEntityManager();

        EntityTransaction tx =
                em.getTransaction();

        try {

            tx.begin();

            em.merge(room);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw new DataAccessException(
                    "Room Update Failed"
            );

        } finally {

            em.close();
        }
    }

    public Optional<Room> findByRoomNumber(
            String roomNumber
    ) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            List<Room> rooms =
                    em.createQuery(
                                    """
                                    SELECT r
                                    FROM Room r
                                    WHERE r.roomNumber = :roomNumber
                                    """,
                                    Room.class
                            )
                            .setParameter(
                                    "roomNumber",
                                    roomNumber
                            )
                            .getResultList();

            return rooms.stream()
                    .findFirst();

        } finally {

            em.close();
        }
    }
}