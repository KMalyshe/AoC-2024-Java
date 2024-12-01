import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day1 {
    
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        ArrayList<String> input = new ArrayList<String>(helper.parseInput("day1.txt"));

        //--- Part 1
        ArrayList<Integer> left = new ArrayList<Integer>(); ArrayList<Integer> right = new ArrayList<Integer>();

        for (String i : input) {
            String[] split = i.split("   ");
            left.add(Integer.valueOf(split[0]));
            right.add(Integer.valueOf(split[1]));
        }
        Collections.sort(left); Collections.sort(right);
        int count = 0;

        for (int i = 0; i<left.size(); i++) count += Math.max(left.get(i), right.get(i)) - Math.min(left.get(i), right.get(i));

        System.out.println("Part 1: " + count);

        //--- Part 2
        HashMap<Integer, Integer> rightSideCount = new HashMap<Integer, Integer>();
        count = 0;

        for (int i : right) {
            if (rightSideCount.keySet().contains(i)) rightSideCount.put(i, rightSideCount.get(i) + 1);
            else rightSideCount.put(i, 1);
        }

        for (int i : left) {
            if (rightSideCount.keySet().contains(i)) count += i*rightSideCount.get(i);
        }

        System.out.println("Part 2: " + count);
    }
}
