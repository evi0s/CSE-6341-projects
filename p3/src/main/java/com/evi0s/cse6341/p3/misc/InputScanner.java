package com.evi0s.cse6341.p3.misc;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputScanner {
    public static InputScanner instance = null;
    public Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public InputScanner(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public synchronized static InputScanner getInstance () {
        if (instance == null) {
            instance = new InputScanner();
        }
        return instance;
    }

    public static synchronized void initializeInstance () {
        if (instance == null) {
            instance = new InputScanner();
        }
    }

    // will throw InputMismatchException if input is not a Long
    public static Long getLong() throws IllegalStateException, NoSuchElementException {
        if (instance != null) {
            return instance.scanner.nextLong();
        }
        throw new IllegalStateException(InputScanner.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

    // will throw InputMismatchException if input is not a Double
    public static Double getDouble() throws IllegalStateException, NoSuchElementException {
        if (instance != null) {
            return instance.scanner.nextDouble();
        }
        throw new IllegalStateException(InputScanner.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

}
