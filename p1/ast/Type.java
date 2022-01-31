package ast;

public enum Type {
    TYPE_INT("int"),
    TYPE_FLOAT("float");

    private final String type;
    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
