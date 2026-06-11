package org.example.cache;

import org.example.entity.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCache {

    private static final Map<String, Customer>
            activeSessions =

            new ConcurrentHashMap<>();

    public static void addSession(
            String email,
            Customer customer
    ) {

        activeSessions.put(
                email,
                customer
        );
    }

    public static Customer getSession(
            String email
    ) {

        return activeSessions.get(email);
    }
}