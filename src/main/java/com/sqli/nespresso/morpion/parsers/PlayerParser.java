package com.sqli.nespresso.morpion.parsers;

import com.sqli.nespresso.morpion.entities.Player;

public class PlayerParser {
    public Player parse(String player) {
        return new Player(player.split(":")[0] , player.split(":")[1]);
    }
}
