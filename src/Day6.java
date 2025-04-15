import Helpers.InputHelper;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        List<String> input = new ArrayList<>(helper.parseInput("day6.txt"));
        List<List<Character>> map = new ArrayList<>();
        int x = 0;
        int y = 0;
        Offset dir = new Offset(0, 0);

        for (int i = 0; i < input.size(); i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).length(); j++) {
                char currentChar = input.get(i).charAt(j);
                map.getLast().add(currentChar);
                if (currentChar == '^' || currentChar == '<' || currentChar == '>' || currentChar == 'v') {
                    x = j;
                    y = i;

                    switch (currentChar) {
                        case '^' -> dir = new Offset(0, -1);
                        case '<' -> dir = new Offset(-1, 0);
                        case '>' -> dir = new Offset(1, 0);
                        case 'v' -> dir = new Offset(0, 1);
                    }
                }
            }
        }

        while (true) { 
            map.get(y).set(x, 'X');
            x += dir.x;
            y += dir.y;
            if (map.size() <= y || map.get(y).size() <= x) break;
            if (map.get(y).get(x) == '#') {
                x -= dir.x;
                y -= dir.y;
                String currentDir = dir.x + "" + dir.y;

                switch (currentDir) {
                    case "0-1" -> dir = new Offset(1, 0);
                    case "-10" -> dir = new Offset(0, -1);
                    case "10" -> dir = new Offset(0, 1);
                    case "01" -> dir = new Offset (-1, 0);
                }
            }
        }

        int count = 0;
        for (List<Character> row : map) {
            for (Character col : row) {
                if (col == 'X') count++;
            }
        }
        for (List<Character> row : map) System.out.println(row);
        System.out.println(count);
    }
}

class Offset {
    public final int x;
    public final int y;

    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
