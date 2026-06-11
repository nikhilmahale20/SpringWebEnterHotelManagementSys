package org.example.thread;

import org.example.entity.Room;
import org.example.enums.RoomStatus;
import org.example.repository.RoomRepository;

import java.util.List;
import java.util.concurrent.Callable;

public class OccupancyReportTask
        implements Callable<Double> {

    private final RoomRepository
            roomRepository =
            new RoomRepository();

    @Override
    public Double call() {

        List<Room> rooms =
                roomRepository.getAllRooms();

        long occupiedRooms =
                rooms.stream()

                        .filter(
                                room ->
                                        room.getStatus()
                                                == RoomStatus.OCCUPIED
                        )

                        .count();

        if (rooms.isEmpty()) {

            return 0.0;
        }

        return (occupiedRooms * 100.0)
                / rooms.size();
    }
}