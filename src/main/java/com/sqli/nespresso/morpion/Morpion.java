package com.sqli.nespresso.morpion;

import com.sqli.nespresso.morpion.entities.Pair;
import com.sqli.nespresso.morpion.parsers.CoordinateParser;
import com.sqli.nespresso.morpion.parsers.PlayerParser;
import com.sqli.nespresso.morpion.entities.Player;

public class Morpion {

    public static final String LINE_SEPARATOR = "\n";

    private MorpionBoard morpionBoard;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Validator validator = new Validator();

    public Morpion(String dimension, String firstPlayer, String secondPlayer) {

        CoordinateParser coordinateParser = new CoordinateParser();
        PlayerParser playerParser = new PlayerParser();
        Pair dim = coordinateParser.parse(dimension);

        this.morpionBoard = new MorpionBoard(dim.getLine(), dim.getColumn());
        this.firstPlayer = playerParser.parse(firstPlayer);
        this.firstPlayer.playIn(morpionBoard);
        this.secondPlayer = playerParser.parse(secondPlayer);
        this.secondPlayer.playIn(morpionBoard);
    }

    public void play(String player, String coordinate_) {
        CoordinateParser coordinateParser = new CoordinateParser();
        Pair coordinate = coordinateParser.parse(coordinate_);
        getPlayer(player).select(coordinate.getLine(), coordinate.getColumn());
    }

    private Player getPlayer(String player) {
        return player.equals(firstPlayer.getName()) ? firstPlayer : secondPlayer;
    }

    public String report() {
        return this.validator.validate(this.morpionBoard, this.firstPlayer, this.secondPlayer);
    }

    public String display() {
        return morpionBoard.display();
    }

}
