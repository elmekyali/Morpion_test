package com.sqli.nespresso.morpion;

import com.sqli.nespresso.morpion.exception.BoxAlreadySelectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MorpionBoard {

    static final String EMPTY_CASE = " ";
    private String[][] board;

    MorpionBoard(int numberOfLine, int numberOfColumn)
    {
        this.board = new String[numberOfLine][numberOfColumn];
        for (int line = 0 ; line < this.board.length ; line++) {
            for (int column = 0 ; column < this.board.length ; column++) {
                this.board[line][column] = EMPTY_CASE;
            }
        }
    }

    public void fill(int line, int column, String character) {
        if(!this.board[line][column].equals(EMPTY_CASE)) throw new BoxAlreadySelectedException();
        this.board[line][column] = character;
    }

    List<String[]> getLines(){
        return Arrays.stream(board).collect(Collectors.toList());
    }

    List<String[]> getColumns(){
        List<String[]> columns = new ArrayList<>();
        for (int column = 0 ; column < this.board.length ; column++){
            int finalColumn = column;
            columns.add(Arrays.stream(this.board).map(strings -> strings[finalColumn])
                    .toArray(String[]::new));
        }
        return columns;
    }

    List<String[]> getDiagonals() {
        List<String[]> diagonals = new ArrayList<>();
        diagonals.add(getFirstDiagonal());
        diagonals.add(getSecondDiagonal());
        return diagonals;
    }

    private String[] getFirstDiagonal() {
        return IntStream.range(0, this.board.length)
                .mapToObj(index -> this.board[index][index])
                .toArray(String[]::new);
    }

    private String[] getSecondDiagonal() {
        return IntStream.range(0, this.board.length)
                .mapToObj(index -> this.board[index][this.board.length - 1 - index])
                .toArray(String[]::new);
    }

    long numberOfEmptyCases() {
        Stream<String> cases = Arrays.stream(board).flatMap(Stream::of);
        return cases.filter(case_ -> case_.equals(EMPTY_CASE)).count();
    }

    String display() {
        return Arrays.stream(board).map(line -> String.join("|", line)).collect(Collectors.joining(Morpion.LINE_SEPARATOR)) + Morpion.LINE_SEPARATOR;
    }
}
