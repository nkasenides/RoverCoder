package model;

public enum RCCellType {

    SAND("Sand"),
    ROCK("Rock"),
    QUICKSAND("Quicksand"),
    GRAVEL("Gravel"),

    ;

    private final String name;

    RCCellType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
