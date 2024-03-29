package ru.sbt.homework02;

import java.io.BufferedReader;
import java.io.IOException;

class Parser {

    public static TradeData parseInput(BufferedReader reader) throws IOException {
        reader.readLine();
        String type = parseType(reader.readLine());
        double price = parsePrice(reader.readLine());
        return new TradeData(type, price);
    }

    private static String parseType(String line) {
        return line.split("=")[1];
    }

    private static double parsePrice(String line) {
        return Double.parseDouble(line.split("=")[1]);
    }
}
