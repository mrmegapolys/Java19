import java.io.BufferedReader;
import java.io.IOException;

class Parser {

    public static TradeData parseInput(BufferedReader reader) {
        String type;
        double price;
        try {
            reader.readLine();
            type = parseType(reader.readLine());
            price = parsePrice(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new TradeData(type, price);
    }

    private static String parseType(String line) {
        return line.split("=")[1];
    }

    private static double parsePrice(String line) {
        return Double.parseDouble(line.split("=")[1]);
    }
}
