package org.example.comparator;

import org.example.entity.Room;

import java.util.Comparator;

public class RoomPriceComparator
        implements Comparator<Room> {

    @Override
    public int compare(Room r1,
                       Room r2) {

        return Double.compare(
                r1.getPricePerNight(),
                r2.getPricePerNight()
        );
    }
}