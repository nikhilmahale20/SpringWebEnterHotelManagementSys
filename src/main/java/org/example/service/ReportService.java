package org.example.service;

import org.example.thread.RevenueReportTask;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.*;
@Service
public class ReportService {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(2);

    public void generateRevenueReport() {

        try {

            Future<BigDecimal> future =
                    executor.submit(
                            new RevenueReportTask()
                    );

            System.out.println(
                    "Generating Revenue Report..."
            );

            BigDecimal revenue =
                    future.get();

            System.out.println(
                    "Total Revenue : ₹"
                            + revenue
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void shutdown() {

        executor.shutdown();
    }
}