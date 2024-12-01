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

        for (int i = 0; i<left.size(); i++) {
            count += Math.max(left.get(i), right.get(i)) - Math.min(left.get(i), right.get(i));
        }

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
        /* --- Golfed Version for fun
        InputHelper h=new InputHelper();ArrayList<String>i=new ArrayList<>(h.parseInput("day1.txt"));
        ArrayList<Integer>l=new ArrayList<>(),r=new ArrayList<>();for(String s:i){String[]x=s.split("   ");l.add(Integer.valueOf(x[0]));r.add(Integer.valueOf(x[1]));}
        Collections.sort(l);Collections.sort(r);int c=0;for(int j=0;j<l.size();j++)c+=Math.abs(l.get(j)-r.get(j));System.out.println("Part 1: "+c);HashMap<Integer,Integer>m=new HashMap<>();c=0;
        for(int n:r)m.put(n,m.getOrDefault(n,0)+1);for(int n:l)if(m.containsKey(n))c+=n*m.get(n);System.out.println("Part 2: "+c);
        */

    }
}
