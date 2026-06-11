package org.example;

import jakarta.persistence.*;
import org.example.entity.Room;
import org.example.enums.RoomStatus;
import org.example.enums.RoomType;

public class MainRoom {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hotelPU");

        EntityManager em =
                emf.createEntityManager();

        EntityTransaction tx =
                em.getTransaction();

        tx.begin();

        Room room1 = new Room(
                "101",
                RoomType.STANDARD,
                2000,
                RoomStatus.AVAILABLE
        );

        Room room2 = new Room(
                "102",
                RoomType.DELUXE,
                5000,
                RoomStatus.AVAILABLE
        );

        Room room3 = new Room(
                "103",
                RoomType.SUITE,
                8000,
                RoomStatus.MAINTENANCE
        );

        em.persist(room1);
        em.persist(room2);
        em.persist(room3);

        tx.commit();

        System.out.println("Rooms Added Successfully");

        em.close();
        emf.close();
    }
}