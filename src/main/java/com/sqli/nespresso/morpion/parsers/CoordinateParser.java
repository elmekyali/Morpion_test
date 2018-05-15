package com.sqli.nespresso.morpion.parsers;


import com.sqli.nespresso.morpion.entities.Pair;

public class CoordinateParser {
    public Pair parse(String dimension) {
        return new Pair(Integer.valueOf(dimension.split("x")[0])
                ,Integer.valueOf(dimension.split("x")[1]));
    }
}
