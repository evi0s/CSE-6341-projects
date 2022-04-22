package com.evi0s.cse6341.p5.errors;

import com.evi0s.cse6341.p5.misc.Location;

public class AbstractEvaluationError extends Error {

    public enum ErrorType {
        UNINITIALIZED_VAR("uninitialized variable"),
        DIV_BY_ZERO("division by zero"),
        DEAD_CODE("dead code");

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


    public AbstractEvaluationError(ErrorType type) {
        super("AbstractEvaluationError: " + type);
        this.type = type;
    }

    public AbstractEvaluationError(String tag, ErrorType type) {
        super("[" + tag + "] " + "AbstractEvaluationError: " + type);
        this.type = type;
    }

    public AbstractEvaluationError(String tag, ErrorType type, Location loc) {
        super("[" + tag + "] " + "AbstractEvaluationError: " + type + " @ " + loc);
        this.type = type;
    }

    public AbstractEvaluationError(String tag, ErrorType type, String reason, Location loc) {
        super("[" + tag + "] " + "AbstractEvaluationError: " + type + " @ " + loc + ", reason: " + reason);
        this.type = type;
    }
}
