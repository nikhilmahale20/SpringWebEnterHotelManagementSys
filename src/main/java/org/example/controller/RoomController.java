package org.example.controller;

import org.springframework.ui.Model;
import org.example.entity.Room;
import org.example.enums.RoomStatus;
import org.example.enums.RoomType;
import org.example.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(
            RoomService roomService
    ) {
        this.roomService = roomService;
    }

    @GetMapping("/add")
    public String addRoomPage() {

        return "add-room";
    }

    @PostMapping("/add")
    public String addRoom(
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("roomType") RoomType roomType,
            @RequestParam("price") double price
    ) {

        Room room = new Room();

        room.setRoomNumber(roomNumber);

        room.setRoomType(roomType);

        room.setPricePerNight(price);

        room.setStatus(
                RoomStatus.AVAILABLE
        );

        roomService.addRoom(room);

        return "redirect:/rooms";
    }

    @GetMapping("/delete")
    public String deleteRoom(
            @RequestParam("id") Long id
    ) {

        roomService.deleteRoom(id);

        return "redirect:/rooms";
    }

    @GetMapping("/edit")
    public String editRoomPage(
            @RequestParam("id") Long id,
            Model model
    ) {

        Room room =
                roomService.getRoomById(id);

        model.addAttribute(
                "room",
                room
        );

        return "edit-room";
    }

    @PostMapping("/edit")
    public String updateRoom(
            @RequestParam("roomId") Long roomId,
            @RequestParam("roomNumber") String roomNumber,
            @RequestParam("roomType") RoomType roomType,
            @RequestParam("price") double price
    ) {

        Room room =
                roomService.getRoomById(
                        roomId
                );

        room.setRoomNumber(
                roomNumber
        );

        room.setRoomType(
                roomType
        );

        room.setPricePerNight(
                price
        );

        roomService.updateRoom(
                room
        );

        return "redirect:/rooms";
    }

    @GetMapping
    public String viewRooms(
            Model model
    ) {
        var rooms = roomService.getAllRooms();

        System.out.println("ROOM COUNT = " + rooms.size());
        
        model.addAttribute(
                "rooms",
                roomService.getAllRooms()
        );

        return "rooms";
    }
}