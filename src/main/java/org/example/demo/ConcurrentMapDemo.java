package org.example.demo;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {

    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String>
                activeSessions =
                new ConcurrentHashMap<>();

        Runnable task1 = () -> {

            activeSessions.put(1, "Customer-A");

            System.out.println(
                    Thread.currentThread().getName()
                            + " Added Customer-A"
            );
        };

        Runnable task2 = () -> {

            activeSessions.put(2, "Customer-B");

            System.out.println(
                    Thread.currentThread().getName()
                            + " Added Customer-B"
            );
        };

        Thread t1 = new Thread(task1);

        Thread t2 = new Thread(task2);

        t1.start();

        t2.start();

        System.out.println(activeSessions);
    }
}