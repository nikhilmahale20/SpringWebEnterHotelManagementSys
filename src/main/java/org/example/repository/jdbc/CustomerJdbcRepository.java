package org.example.repository.jdbc;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class CustomerJdbcRepository {

    public void fetchCustomerEmails() {

        String sql =
                "SELECT email FROM customers";

        try (

                Connection connection =

                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getString("email")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}