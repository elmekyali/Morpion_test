package com.sqli.nespresso.morpion;

import com.sqli.nespresso.morpion.entities.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Validator {

    String validate(MorpionBoard morpionBoard, Player firstPlayer, Player secondPlayer) {

        Collection<String[]> allLines = Stream.of(
                morpionBoard.getColumns(),
                morpionBoard.getLines(),
                morpionBoard.getDiagonals()
        ).flatMap(Collection::stream).collect(Collectors.toList());

        Optional<String> result = winner(firstPlayer, secondPlayer, allLines);
        if (result.isPresent()) return String.format("Game Over, %s is a winner", result.get());

        if (isEquality(allLines))
            return "Game Over, equality";

        long numberOfEmptyCases = morpionBoard.numberOfEmptyCases();
        return String.format("%d games for %s, %d games for %s"
                , numberOfEmptyCases / 2 + numberOfEmptyCases % 2
                , firstPlayer.getName()
                , numberOfEmptyCases / 2
                , secondPlayer.getName());
    }

    private boolean isEquality(Collection<String[]> allLines) {
        return allLines.stream().flatMap(Stream::of).noneMatch(slot -> slot.equals(MorpionBoard.EMPTY_CASE));
    }

    private Optional<String> winner(Player firstPlayer, Player secondPlayer, Collection<String[]> allLines) {
        return allLines
                .stream()
                .filter(this::areAllSelectedByOnePlayer)
                .map(line -> isSelectedBy(line, firstPlayer, secondPlayer))
                .findFirst();
    }

    private boolean areAllSelectedByOnePlayer(String[] line) {
        return Arrays.stream(line).allMatch(slot -> !slot.equals(MorpionBoard.EMPTY_CASE) && slot.equals(line[0]));
    }

    private String isSelectedBy(String[] line, Player firstPlayer, Player secondPlayer) {
        return line[0].equals(firstPlayer.getCharacter()) ? firstPlayer.getName() : secondPlayer.getName();
    }
}
