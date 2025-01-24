import java.util.ArrayList;

public class Day4 {
    
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        ArrayList<String> input = new ArrayList<String>(helper.parseInput("day4.txt"));
        ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();

        for (int i = 0; i<input.size(); i++) {
            charMatrix.add(new ArrayList<Character>());
            for (int j = 0; j<input.get(i).length(); j++) {
                charMatrix.get(i).add(input.get(i).charAt(j));
            }
        }

        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i<charMatrix.size(); i++) {
            for (int j = 0; j<charMatrix.get(0).size(); j++) {
                if (charMatrix.get(i).get(j) == 'X') {
                    ArrayList<Offset> found = checkCharAdjacent('M',new int[]{i, j}, charMatrix);
                    for (Offset offset : found) {
                        // Letter at Offset = M -> Further letters must be A and S

                        // I AM SORRY FOR THE FOLLOWING CODE
                        // I AM NOT WRITING ANOTHER checkM
                        // :(
                        try {
                            if (charMatrix.get(i + offset.y*2).get(j + offset.x*2) == 'A' 
                            && charMatrix.get(i + offset.y*3).get(j + offset.x*3) == 'S') count1++;
                        } catch (Exception e) {
                        }
                    }
                }
                if (charMatrix.get(i).get(j) == 'A') {
                    int countMas = 0;
                    ArrayList<Offset> found = checkCharAdjacent('M',new int[]{i, j}, charMatrix);
                    if (found.size() < 2) continue;
                    for (Offset offset : found) {
                        if (offset.x != 0 && offset.y != 0) {
                            try {
                                if (charMatrix.get(i - offset.y).get(j - offset.x) == 'S') countMas++;
                            } catch (Exception e) {
                            }
                        }
                    }
                    if (countMas == 2) {
                        System.out.println("Found X-MAS at " + i + ", " + j);
                        count2++;
                    }
                }
            }
        }

        System.out.println("XMAS amount: " + count1);
        System.out.println("X-MAS amount: " + count2);

    }

    //coords: [row, column]
    
    public static ArrayList<Offset> checkCharAdjacent (char search, int[] coordinates, ArrayList<ArrayList<Character>> charMatrix) {
        ArrayList<Offset> foundChars = new ArrayList<>();
        
        Offset[] offsets = new Offset[]{
            new Offset(0, -1),
            new Offset(-1, -1),
            new Offset(-1, 0),
            new Offset(-1, 1),
            new Offset(0, 1),
            new Offset(1, 1),
            new Offset(1, 0),
            new Offset(1, -1),
        };
        
        for (Offset offset : offsets) {
            if (offset.y == -1 && coordinates[0] == 0) continue;
            if (offset.y == 1 && coordinates[0] == charMatrix.get(0).size()-1) continue;
            if (offset.x == -1 && coordinates[1] == 0) continue;
            if (offset.x == 1 && coordinates[1] == charMatrix.size()-1) continue;

            if (charMatrix.get(coordinates[0] + offset.y).get(coordinates[1] + offset.x) == search) {
                foundChars.add(offset);
            }
        }
        return foundChars;
    }

    

}

class Offset {
    public int x;
    public int y;

    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



