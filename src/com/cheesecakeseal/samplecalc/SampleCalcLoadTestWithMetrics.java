package com.cheesecakeseal.samplecalc;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleCalcLoadTestWithMetrics {

    @Test
    public void loadTestWithMetrics() throws InterruptedException {
        int maxThreads = 2000; // Adjust this number to increase load until maximum strain is found
        int incrementStep = 200; // Number of threads to add in each increment
        int runDurationMillis = 10000; // Duration to run each load increment, in milliseconds

        for (int threadCount = incrementStep; threadCount <= maxThreads; threadCount += incrementStep) {
            System.out.println("Running load test with " + threadCount + " threads");

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                threads.add(new Thread(() -> {
                    ByteArrayInputStream input = new ByteArrayInputStream("5\n+\n3\n".getBytes());
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(output);

                    SampleCalc.runCalculator(input, printStream);

                    assertTrue(output.toString().contains("The result is: 8.0"));
                }));
            }

            // Start threads and collect CPU and memory stats while running
            long startTime = System.currentTimeMillis();
            for (Thread thread : threads) {
                thread.start();
            }

            while (System.currentTimeMillis() - startTime < runDurationMillis) {
                printSystemMetrics();
                Thread.sleep(500); // Collect metrics every 500ms
            }

            // Wait for all threads to complete
            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println("Finished load test with " + threadCount + " threads\n");
        }
    }

    private void printSystemMetrics() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        // CPU Load - may require additional configurations for certain platforms

    }
}