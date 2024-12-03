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

        List<String> matches = new ArrayList<String>();
        Matcher match = Pattern.compile("mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\)").matcher(fullText);
        while (match.find()) {
            matches.add(match.group());
        }

        int count = 0;

        for (int i = 0; i<matches.size(); i++) {
            String[] split = matches.get(i).split(",");
            split[0] = split[0].substring(4); split[1] = split[1].substring(0, split[1].length()-1);
            count += Integer.valueOf(split[0]) * Integer.valueOf(split[1]);
        }

        System.out.println(count);
    }
}
