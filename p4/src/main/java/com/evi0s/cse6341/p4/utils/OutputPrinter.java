package com.evi0s.cse6341.p4.utils;


import java.io.PrintStream;

public class OutputPrinter {
    public static OutputPrinter instance = null;
    public PrintStream printer;

    public OutputPrinter() {
        this.printer = System.out;
    }

    public OutputPrinter(PrintStream printStream) {
        this.printer = printStream;
    }

    public synchronized static OutputPrinter getInstance () {
        if (instance == null) {
            instance = new OutputPrinter();
        }
        return instance;
    }

    public static synchronized void initializeInstance () {
        if (instance == null) {
            instance = new OutputPrinter();
        }
    }

    // will throw InputMismatchException if input is not a Long
    public static void print(String message) throws IllegalStateException {
        if (instance != null) {
            instance.printer.print(message);
            instance.printer.print("\n");
            return;
        }
        throw new IllegalStateException(OutputPrinter.class.getSimpleName() +
                " is not initialized, call initializeInstance(..) method first.");
    }

}
