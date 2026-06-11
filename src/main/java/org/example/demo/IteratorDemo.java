package org.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {

    public static void main(String[] args) {

        List<String> rooms =
                new ArrayList<>();

        rooms.add("101");

        rooms.add("102");

        rooms.add("103");

        try {

            for (String room : rooms) {

                if (room.equals("102")) {

                    rooms.remove(room);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Fail-Fast Exception Triggered"
            );
        }

        System.out.println(
                "\nSafe Removal Using Iterator"
        );

        Iterator<String> iterator =
                rooms.iterator();

        while (iterator.hasNext()) {

            String room =
                    iterator.next();

            if (room.equals("101")) {

                iterator.remove();
            }
        }

        System.out.println(rooms);
    }
}