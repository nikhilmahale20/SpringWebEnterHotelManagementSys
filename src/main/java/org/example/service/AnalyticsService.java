package org.example.service;

import org.example.comparator.RoomPriceComparator;
import org.example.entity.Room;
import org.example.enums.RoomStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    public void analyzeRooms(List<Room> rooms) {

        System.out.println("\n===== SORT BY ROOM NUMBER =====");

        Collections.sort(rooms);

        rooms.forEach(System.out::println);

        System.out.println(
                "\n===== SORT BY PRICE ====="
        );

        rooms.sort(
                new RoomPriceComparator()
        );

        rooms.forEach(System.out::println);

        System.out.println(
                "\n===== AVAILABLE ROOMS ====="
        );

        List<Room> availableRooms =
                rooms.stream()

                        .filter(room ->
                                room.getStatus()
                                        == RoomStatus.AVAILABLE
                        )

                        .toList();

        availableRooms.forEach(System.out::println);

        System.out.println(
                "\n===== ROOM COUNT BY STATUS ====="
        );

        Map<RoomStatus, Long> roomCountMap =

                rooms.stream()

                        .collect(
                                Collectors.groupingBy(
                                        Room::getStatus,
                                        Collectors.counting()
                                )
                        );

        roomCountMap.forEach(
                (status, count) ->

                        System.out.println(
                                status + " : " + count
                        )
        );

        System.out.println(
                "\n===== MOST EXPENSIVE ROOM ====="
        );

        Room expensiveRoom =

                Collections.max(
                        rooms,
                        new RoomPriceComparator()
                );

        System.out.println(expensiveRoom);

        System.out.println(
                "\n===== CHEAPEST ROOM ====="
        );

        Room cheapestRoom =

                Collections.min(
                        rooms,
                        new RoomPriceComparator()
                );

        System.out.println(cheapestRoom);
    }
}