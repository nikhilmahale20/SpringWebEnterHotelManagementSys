package org.example.demo;

import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<Integer> vipQueue =
                new PriorityQueue<>();

        vipQueue.add(3);

        vipQueue.add(1);

        vipQueue.add(2);

        while (!vipQueue.isEmpty()) {

            System.out.println(
                    "Processing VIP : "
                            + vipQueue.poll()
            );
        }
    }
}