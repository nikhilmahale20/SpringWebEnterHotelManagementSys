package org.example.service;

import org.example.entity.Booking;
import org.example.entity.Customer;
import org.example.entity.Room;
import org.example.enums.BookingStatus;
import org.example.enums.RoomStatus;
import org.example.exception.RoomUnavailableException;
import org.example.repository.BookingRepository;
import org.example.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository =
            new BookingRepository();

    private final RoomRepository roomRepository =
            new RoomRepository();

    public void bookRoom(Customer customer,
                         Long roomId,
                         LocalDate checkIn,
                         LocalDate checkOut) {

        Optional<Room> optionalRoom =
                roomRepository.findRoomById(roomId);

        Room room = optionalRoom.orElseThrow(
                () -> new RoomUnavailableException(
                        "Room Not Found"
                )
        );

        if (!checkOut.isAfter(checkIn)) {

            throw new RuntimeException(
                    "Check-out date must be after check-in date"
            );
        }
        if (room.getStatus() != RoomStatus.AVAILABLE) {

            throw new RoomUnavailableException(
                    "Room Not Available"
            );
        }

        List<Booking> existingBookings =
                bookingRepository.getBookingsByRoomId(
                        roomId
                );

        for (Booking booking : existingBookings) {

            boolean overlap =
                    checkIn.isBefore(
                            booking.getCheckOutDate()
                    )
                            &&
                            checkOut.isAfter(
                                    booking.getCheckInDate()
                            );

            if (overlap) {

                throw new RoomUnavailableException(
                        "Room Already Booked For Selected Dates"
                );
            }
        }

        Booking booking = new Booking();

        booking.setCustomer(customer);

        booking.setRoom(room);

        booking.setCheckInDate(checkIn);

        booking.setCheckOutDate(checkOut);

        booking.setBookingStatus(
                BookingStatus.CONFIRMED
        );

        bookingRepository.saveBooking(
                booking
        );

        room.setStatus(
                RoomStatus.OCCUPIED
        );

        roomRepository.updateRoom(
                room
        );

        System.out.println(
                "Booking Successful"
        );
    }

    public List<Booking> getAllBookings() {

        return bookingRepository
                .getAllBookings();
    }

    public List<Booking> getCustomerBookings(
            Long customerId
    ) {

        return bookingRepository
                .getBookingsByCustomerId(
                        customerId
                );
    }

    public List<Booking> getBookingsByRoomId(
            Long roomId
    ) {

        return bookingRepository
                .getBookingsByRoomId(
                        roomId
                );
    }


    public void checkOutBooking(Long bookingId) {

        Booking booking =
                bookingRepository.findById(
                        bookingId
                );

        if (booking == null) {

            throw new RuntimeException(
                    "Booking Not Found"
            );
        }

        booking.setBookingStatus(
                BookingStatus.CHECKED_OUT
        );

        Room room =
                booking.getRoom();

        room.setStatus(
                RoomStatus.AVAILABLE
        );

        bookingRepository.updateBooking(
                booking
        );

        roomRepository.updateRoom(
                room
        );
    }
    public void cancelBooking(Long bookingId) {

        Booking booking =
                bookingRepository.findById(
                        bookingId
                );

        if (booking == null) {

            throw new RuntimeException(
                    "Booking Not Found"
            );
        }

        booking.setBookingStatus(
                BookingStatus.CANCELLED
        );

        Room room =
                booking.getRoom();

        room.setStatus(
                RoomStatus.AVAILABLE
        );

        bookingRepository.updateBooking(
                booking
        );

        roomRepository.updateRoom(
                room
        );
    }


    
}