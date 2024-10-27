package com.cheesecakeseal.testcalc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleCalcTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    public void testAddition() {
        provideInput("5\n+\n3\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("The result is: 8.0"));
    }

    @Test
    public void testSubtraction() {
        provideInput("10\n-\n4\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("The result is: 6.0"));
    }

    @Test
    public void testMultiplication() {
        provideInput("6\n*\n7\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("The result is: 42.0"));
    }

    @Test
    public void testDivision() {
        provideInput("20\n/\n5\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("The result is: 4.0"));
    }

    @Test
    public void testInvalidOperator() {
        provideInput("10\n%\n3\n+\n3\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("Invalid Operator"));
    }

    @Test
    public void testDivisionByZero() {
        provideInput("10\n/\n0\n");
        SampleCalc.main(new String[]{});
        assertTrue(outputStream.toString().contains("Error: Division by zero is not allowed."));
    }
}
