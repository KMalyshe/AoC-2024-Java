import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Day3 {
    public static void main(String[] args) {
        InputHelper helper = new InputHelper();
        ArrayList<String> input = new ArrayList<String>(helper.parseInput("day3.txt"));
        String fullText = "";

        for (String line : input) {
            fullText += line.replace("\n", "");
        }

        List<String> matchesPart1 = regex(fullText, "mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\)");
        List<String> matchesPart2 = regex(fullText, "(?:mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\)|do\\(\\)|don't\\(\\))");

        int count1 = 0;

        for (int i = 0; i<matchesPart1.size(); i++) {
            String[] split = matchesPart1.get(i).split(",");
            split[0] = split[0].substring(4); split[1] = split[1].substring(0, split[1].length()-1);
            count1 += Integer.valueOf(split[0]) * Integer.valueOf(split[1]);
        }

        System.out.println("Part 1: " + count1);

        boolean doMult = true;
        int count2 = 0;

        for (int i = 0; i<matchesPart2.size(); i++) {
            String match = matchesPart2.get(i);
            if (match.equals("do()")) {doMult = true; continue;}
            else if (match.equals("don't()")) {doMult = false; continue;}
            else if (doMult) {
                String[] split = match.split(",");
                split[0] = split[0].substring(4); split[1] = split[1].substring(0, split[1].length()-1);
                count2 += Integer.valueOf(split[0]) * Integer.valueOf(split[1]);
            }
        }

        System.out.println("Part 2: " + count2);

    }

    private static List<String> regex(String text, String reg) {
        List<String> list = new ArrayList<String>();
        Matcher matchPart1 = Pattern.compile(reg).matcher(text);
        while (matchPart1.find()) {
            list.add(matchPart1.group());
        }
        return list;
    }
}
