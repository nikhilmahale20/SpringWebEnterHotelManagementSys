package org.example.entity;

import jakarta.persistence.*;
import org.example.enums.RoomStatus;
import org.example.enums.RoomType;

import java.util.List;

import jakarta.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "rooms")
//@Cacheable
//@Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Room   implements Comparable<Room> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;


    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(mappedBy = "room",
            cascade = CascadeType.ALL)

    private List<Booking> bookings;
    @Column(nullable = false)
    private double pricePerNight;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public Room() {
    }

    public Room(String roomNumber,
                RoomType roomType,
                double pricePerNight,
                RoomStatus status) {

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType=" + roomType +
                ", pricePerNight=" + pricePerNight +
                ", status=" + status +
                '}';
    }

    @Override
    public int compareTo(Room otherRoom) {

        return this.getRoomNumber()
                .compareTo(otherRoom.getRoomNumber());
    }
}