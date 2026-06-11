package org.example.deadlock;

public class DeadlockDemo {

    private static final Object ROOM_LOCK =
            new Object();

    private static final Object PAYMENT_LOCK =
            new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (ROOM_LOCK) {

                System.out.println(
                        "Thread 1 locked ROOM"
                );

                synchronized (PAYMENT_LOCK) {

                    System.out.println(
                            "Thread 1 locked PAYMENT"
                    );
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (PAYMENT_LOCK) {

                System.out.println(
                        "Thread 2 locked PAYMENT"
                );

                synchronized (ROOM_LOCK) {

                    System.out.println(
                            "Thread 2 locked ROOM"
                    );
                }
            }
        });

        t1.start();

        t2.start();
    }
}