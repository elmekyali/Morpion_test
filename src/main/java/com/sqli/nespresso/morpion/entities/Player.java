package com.sqli.nespresso.morpion.entities;

import com.sqli.nespresso.morpion.MorpionBoard;

public class Player {
    private final String name;
    private final String character;

    public Player(String name, String character) {
        this.name = name;
        this.character = character;
    }

    public void select(int line, int column, MorpionBoard morpionBoard) {
        morpionBoard.fill(line, column, character);
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }
}
