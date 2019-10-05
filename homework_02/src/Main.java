import trades.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        String filepath = args[0];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        TradeData tradeData = Parser.parseInput(reader);
        if (tradeData == null) return;

        Trade trade1 = TradeCreator.getTrade1(tradeData);
        Trade trade2 = TradeCreator.getTrade2(tradeData);
        System.out.println(trade1.toString());
        System.out.println(trade2.toString());
    }
}
