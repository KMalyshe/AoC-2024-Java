import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        ArrayList<String> input = new ArrayList<String>(helper.parseInput("day2.txt"));
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i<input.size(); i++) {
            // Part One-----
            Tuple<Boolean, Integer> tuple = checkSafe(input.get(i).split(" "));
            boolean valid = tuple.x;
            if (valid) {count1++; count2++;}
            
            // -------------
            // Part Two-----
            else {
                List<String> split = Arrays.asList(input.get(i).split(" "));
                ArrayList<String> test1 = (new ArrayList<>(split)); ArrayList<String> test2 = (new ArrayList<>(split)); ArrayList<String> test3 = (new ArrayList<>(split));
                test1.remove(tuple.y-1);

                // WHY DO I NEED TO DO +0 FOR INTEGER TO WORK PROPERLY THIS IS STUPID
                test2.remove(tuple.y+0);

                // check if reversing the order makes it fine
                test3.remove(0);
                
                if (checkSafe(toStrArray(test1)).x || checkSafe(toStrArray(test2)).x ||checkSafe(toStrArray(test3)).x) {count2++;}

            }
            

        }
        System.out.println(count1);
        System.out.println(count2);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Tuple<Boolean, Integer> checkSafe(String[] input) {

            boolean ascending = Integer.valueOf(input[0]) < Integer.valueOf(input[1]);
            boolean valid = true;

            for (int j = 1; j<input.length; j++) {

                int left = Integer.valueOf(input[j-1]); int right = Integer.valueOf(input[j]);
                int diff = Math.abs(Integer.valueOf(input[j-1]) - Integer.valueOf(input[j]));
                valid = (diff < 4) && (diff > 0);
                if (!valid) return new Tuple(false, j);

                valid = ascending ? left < right : left > right;
                if (!valid) return new Tuple(false, j);    
            }

            return new Tuple(true, -1);
    }

    public static String[] toStrArray(ArrayList<String> input) {
        String[] returnArr = new String[input.size()];
        for (int i = 0; i<input.size(); i++) {
            returnArr[i] = input.get(i);
        }
        return returnArr;
    }

    public static class Tuple<Boolean, Integer> {
        public final Boolean x;
        public final Integer y;
        public Tuple(Boolean x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    
}
