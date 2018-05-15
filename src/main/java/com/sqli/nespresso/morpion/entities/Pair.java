package com.sqli.nespresso.morpion.entities;

public class Pair {
    private final int line;
    private final int column;

    public Pair(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
