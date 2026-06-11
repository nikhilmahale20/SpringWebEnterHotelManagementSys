package org.example.demo;

public class DeadlockDemo {

    private static final Object ROOM_LOCK =
            new Object();

    private static final Object BOOKING_LOCK =
            new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (ROOM_LOCK) {

                System.out.println(
                        "Thread 1 Locked ROOM"
                );

                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {
                }

                synchronized (BOOKING_LOCK) {

                    System.out.println(
                            "Thread 1 Locked BOOKING"
                    );
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (BOOKING_LOCK) {

                System.out.println(
                        "Thread 2 Locked BOOKING"
                );

                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {
                }

                synchronized (ROOM_LOCK) {

                    System.out.println(
                            "Thread 2 Locked ROOM"
                    );
                }
            }
        });

        t1.start();

        t2.start();
    }
}