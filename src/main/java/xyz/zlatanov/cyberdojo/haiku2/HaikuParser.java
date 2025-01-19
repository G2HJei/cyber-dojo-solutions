package xyz.zlatanov.cyberdojo.haiku2;

public class HaikuParser {
    public Haiku[] parse(String input) {
        var rows = input.split("\n");
        var result = new Haiku[rows.length];
        for (int i = 0; i < rows.length; i++) {
            var row = rows[i];
            result[i] = new Haiku(row);
        }
        return result;
    }
}
