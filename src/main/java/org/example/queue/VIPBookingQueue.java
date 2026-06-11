package org.example.queue;

import java.util.PriorityQueue;

public class VIPBookingQueue {

    private final PriorityQueue<String>
            vipQueue =

            new PriorityQueue<>();

    public void addVIPCustomer(
            String customerName
    ) {

        vipQueue.offer(customerName);
    }

    public void processVIPCustomer() {

        System.out.println(
                "Processing : "
                        + vipQueue.poll()
        );
    }
}