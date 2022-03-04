package com.evi0s.cse6341.p3.errors;

import com.evi0s.cse6341.p3.misc.Location;

public class RuntimeError extends Error {

    public enum ErrorType {
        UNINITIALIZED_VAR("uninitialized variable"),
        DIV_BY_ZERO("division by zero"),
        FAILED_STDIN_READ("failed to read from stdin");

        private final String type;
        ErrorType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    public ErrorType type;


    public RuntimeError(ErrorType type) {
        super("RuntimeError: " + type);
        this.type = type;
    }

    public RuntimeError(String tag, ErrorType type) {
        super("[" + tag + "] " + "RuntimeError: " + type);
        this.type = type;
    }

    public RuntimeError(String tag, ErrorType type, Location loc) {
        super("[" + tag + "] " + "RuntimeError: " + type + " @ " + loc);
        this.type = type;
    }

    public RuntimeError(String tag, ErrorType type, String reason, Location loc) {
        super("[" + tag + "] " + "RuntimeError: " + type + " @ " + loc + ", reason: " + reason);
        this.type = type;
    }
}
