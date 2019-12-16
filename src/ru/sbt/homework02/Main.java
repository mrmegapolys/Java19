package ru.sbt.homework02;

import ru.sbt.homework02.trades.Trade;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        TradeData tradeData;
        try {
            tradeData = Parser.parseInput(reader);
        } catch (IOException e) {
            return;
        }

        Trade trade1 = TradeCreator.getTrade1(tradeData);
        Trade trade2 = TradeCreator.getTrade2(tradeData);
        System.out.println(trade1.toString());
        System.out.println(trade2.toString());
    }
}
