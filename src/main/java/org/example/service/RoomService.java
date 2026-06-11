package org.example.service;

import org.example.entity.Customer;
import org.example.entity.Room;
import org.example.enums.Role;
import org.example.enums.RoomStatus;
import org.example.exception.BusinessException;
import org.example.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(
            RoomRepository roomRepository
    ) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room) {

        if (room.getRoomNumber() == null
                || room.getRoomNumber().isBlank()) {

            throw new BusinessException(
                    "Room Number Required"
            );
        }

        if (room.getPricePerNight() <= 0) {

            throw new BusinessException(
                    "Invalid Room Price"
            );
        }

        roomRepository
                .findByRoomNumber(
                        room.getRoomNumber()
                )
                .ifPresent(r -> {
                    throw new BusinessException(
                            "Room Number Already Exists"
                    );
                });

        roomRepository.saveRoom(room);
    }
    public Room getRoomById(Long id) {

        return roomRepository
                .findRoomById(id)
                .orElseThrow(
                        () -> new BusinessException(
                                "Room Not Found"
                        )
                );
    }

    public void updateRoom(Room room) {

        roomRepository.updateRoom(room);
    }

    public void deleteRoom(Long roomId) {

        roomRepository.deleteRoom(roomId);
    }

    public List<Room> getAllRooms() {

        return roomRepository.getAllRooms();
    }

    public boolean isRoomAvailable(Long roomId) {

        return roomRepository
                .findRoomById(roomId)
                .map(room ->
                        room.getStatus()
                                == RoomStatus.AVAILABLE
                )
                .orElse(false);
    }
}