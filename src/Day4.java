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

        int count = 0;
        for (int i = 0; i<charMatrix.size(); i++) {
            for (int j = 0; j<charMatrix.get(0).size(); j++) {
                if (charMatrix.get(i).get(j) == 'X') {
                    ArrayList<Offset> found = checkM(new int[]{i, j}, charMatrix);
                    for (Offset offset : found) {
                        // Letter at Offset = M -> Further letters must be A and S

                        // I AM SORRY FOR THE FOLLOWING CODE
                        // I AM NOT WRITING ANOTHER checkM
                        // :(
                        try {
                            if (charMatrix.get(i + offset.x*2).get(j + offset.y*2) == 'A' 
                            && charMatrix.get(i + offset.x*3).get(j + offset.y*3) == 'S') count++;
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }

        System.out.println("XMAS amount: " + count);

    }

    //coords: [row, column]
    public static ArrayList<Offset> checkM (int[] coordinates, ArrayList<ArrayList<Character>> charMatrix) {
        ArrayList<Offset> foundM = new ArrayList<>();
        //topmost row
        if (coordinates[0] == 0) {
            //leftmost
            if (coordinates[1] == 0) {
                if (charMatrix.get(0).get(1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(1).get(1) == 'M') foundM.add(new Offset(1, 1));
                if (charMatrix.get(1).get(0) == 'M') foundM.add(new Offset(1, 0));
            }
            //rightmost
            else if (coordinates[1] == charMatrix.get(0).size()-1) {
                if (charMatrix.get(0).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(1, -1));
                if (charMatrix.get(1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
            }
            else {
                if (charMatrix.get(0).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(1, -1));
                if (charMatrix.get(1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
                if (charMatrix.get(0).get(coordinates[1]+1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(1, 1));
            }
        }
        // bottommost row
        else if (coordinates[0] == charMatrix.size()-1) {
            //leftmost
            if (coordinates[1] == 0) {
                if (charMatrix.get(coordinates[0]).get(1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(coordinates[0]-1).get(1) == 'M') foundM.add(new Offset(-1, 1));
                if (charMatrix.get(coordinates[0]-1).get(0) == 'M') foundM.add(new Offset(-1, 0));
            }
            //rightmost
            else if (coordinates[1] == charMatrix.get(0).size()-1) {
                if (charMatrix.get(coordinates[0]).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(-1, -1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]) == 'M') foundM.add(new Offset(-1, 0));
            }
            else {
                if (charMatrix.get(coordinates[0]).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(-1, -1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
                if (charMatrix.get(coordinates[0]).get(coordinates[1]+1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(-1, 1));
            }
        }
        else {
            //leftmost
            if (coordinates[1] == 0) {
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]) == 'M') foundM.add(new Offset(-1, 0));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(-1, 1));
                if (charMatrix.get(coordinates[0]).get(coordinates[1]+1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(1, 1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
            }
            //rightmost
            else if (coordinates[1] == charMatrix.get(0).size()-1) {
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]) == 'M') foundM.add(new Offset(-1, 0));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(-1, -1));
                if (charMatrix.get(coordinates[0]).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(1, -1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
            }
            else {
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]) == 'M') foundM.add(new Offset(-1, 0));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(-1, -1));
                if (charMatrix.get(coordinates[0]).get(coordinates[1]-1) == 'M') foundM.add(new Offset(0, -1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]-1) == 'M') foundM.add(new Offset(1, -1));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]) == 'M') foundM.add(new Offset(1, 0));
                if (charMatrix.get(coordinates[0]+1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(1, 1));
                if (charMatrix.get(coordinates[0]).get(coordinates[1]+1) == 'M') foundM.add(new Offset(0, 1));
                if (charMatrix.get(coordinates[0]-1).get(coordinates[1]+1) == 'M') foundM.add(new Offset(-1, 1));
            }
        }

        return foundM;
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



